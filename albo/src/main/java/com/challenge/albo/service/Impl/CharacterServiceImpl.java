package com.challenge.albo.service.Impl;

import com.challenge.albo.dto.CharacterDataWrapper;
import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.exception.MarvelException;
import com.challenge.albo.mapper.CharacterMapper;
import com.challenge.albo.model.CharacterDO;
import com.challenge.albo.model.Comic;
import com.challenge.albo.repository.CharacterRepository;
import com.challenge.albo.repository.ComicRepository;
import com.challenge.albo.service.CharacterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.challenge.albo.Util.MarvelConstants.CHARACTERS;
import static com.challenge.albo.Util.MarvelConstants.SERVER_ERROR;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;
    private static Gson gson;

    @Override
    public CharacterResponseWrapper getCharacterInformation() throws MarvelException {
        CharacterDataWrapper character = new CharacterDataWrapper();
        gson = new GsonBuilder().create();
        try {
            String response = MarvelApiClient.sendRequest(CHARACTERS);
            character = gson.fromJson(response, CharacterDataWrapper.class);
            log.info("Json después de consumir la API: {}", character.toString());
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new MarvelException(SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return wrapperMapToCharacter(character);
    }

    private CharacterResponseWrapper wrapperMapToCharacter(CharacterDataWrapper character) {
        CharacterResponseWrapper characterResponseWrapper = CharacterMapper.buildCharacter(character);
        characterResponseWrapper.setLastSync(new Date());
        syncCharacterWithDb(characterResponseWrapper);
        log.info("Objeto despues de mapear el json: {}", characterResponseWrapper);
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
        log.info("Sincronizar entidad character en la base de datos: {}", charactersToSave);
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
        log.info("Sincronizar entidad comic  en la base de datos: {}", comicsToSave);
        comicRepository.saveAll(comicsToSave);
    }
}
