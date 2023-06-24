package com.challenge.albo.service.Impl;

import com.challenge.albo.dto.CharacterDataWrapper;
import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.mapper.CharacterMapper;
import com.challenge.albo.model.CharacterDO;
import com.challenge.albo.model.Comic;
import com.challenge.albo.repository.CharacterRepository;
import com.challenge.albo.repository.ComicRepository;
import com.challenge.albo.service.CharacterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.challenge.albo.Util.MarvelConstants.CHARACTERS;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;
    private static Gson gson;

    @Override
    public CharacterResponseWrapper getCharacterInformation() {
        CharacterDataWrapper character = new CharacterDataWrapper();
        gson = new GsonBuilder().create();
        try {
            String response = MarvelApiClient.sendRequest(CHARACTERS);
            character = gson.fromJson(response, CharacterDataWrapper.class);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return wrapperMapToCharacter(character);
    }

    private CharacterResponseWrapper wrapperMapToCharacter(CharacterDataWrapper character) {
        CharacterResponseWrapper characterResponseWrapper = CharacterMapper.buildCharacter(character);
        characterResponseWrapper.setLastSync(new Date());
        syncCharacterWithDb(characterResponseWrapper);
        return characterResponseWrapper;
    }

    private void syncCharacterWithDb(CharacterResponseWrapper characterResponseWrapper) {
        List<CharacterDO> charactersToSave = characterResponseWrapper.getCharacters().stream()
                .map(characterResponse -> {
                    CharacterDO characterDO = new CharacterDO();
                    characterDO.setCreated(LocalDate.now().atStartOfDay());
                    characterDO.setLastSync(characterResponse.getLastSync().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                    characterDO.setName(characterResponse.getName());
                    syncComicWithDb(characterResponse.getComics());
                    return characterDO;
                })
                .collect(Collectors.toList());

        characterRepository.saveAll(charactersToSave);
    }

    private void syncComicWithDb(List<String> comics) {
        List<Comic> comicsToSave = comics.stream()
                .map(c -> {
                    Comic comic = new Comic();
                    comic.setName(c);
                    return comic;
                })
                .collect(Collectors.toList());

        comicRepository.saveAll(comicsToSave);
    }
}
