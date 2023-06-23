package com.challenge.albo.service.Impl;

import com.challenge.albo.dto.CharacterDataWrapper;
import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.mapper.CharacterMapper;
import com.challenge.albo.model.CharacterDO;
import com.challenge.albo.model.Comic;
import com.challenge.albo.repository.CharacterRepository;
import com.challenge.albo.service.CharacterService;
import com.challenge.albo.service.MarvelApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;
    private static Gson gson;

    @Override
    public CharacterResponseWrapper getCharacterInformation() {
        CharacterDataWrapper character = new CharacterDataWrapper();
        gson = new GsonBuilder().create();
        try {
            String response = MarvelApiClient.sendRequest("characters");
            character = gson.fromJson(response, CharacterDataWrapper.class);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return wrapperMapToCharacter(character);
    }

    private CharacterResponseWrapper wrapperMapToCharacter(CharacterDataWrapper character) {
        CharacterResponseWrapper characterResponseWrapper = CharacterMapper.buildCharacter(character);
        characterResponseWrapper.setLastSync(new Date());
        syncWithDb(characterResponseWrapper);
        return characterResponseWrapper;
    }

    private void syncWithDb(CharacterResponseWrapper characterResponseWrapper) {
        Set<Comic> comicSet;

        characterResponseWrapper.getCharacters().forEach(characterResponse -> {
            CharacterDO characterDO = new CharacterDO();
            characterDO.setCreated(LocalDate.now().atStartOfDay());
            characterDO.setLastSync(characterResponse.getLastSync().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            characterDO.setName(characterResponse.getName());
            characterDO.setComics();
            characterRepository.save(characterDO);
        });
    }
}
