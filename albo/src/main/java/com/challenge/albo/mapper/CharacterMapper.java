package com.challenge.albo.mapper;

import com.challenge.albo.dto.CharacterDataJson;
import com.challenge.albo.dto.CharacterDataWrapper;
import com.challenge.albo.dto.CharacterResponse;
import com.challenge.albo.dto.CharacterResponseWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterMapper {
    public static CharacterResponseWrapper buildCharacter(CharacterDataWrapper characterDataWrapper) {
        List<CharacterResponse> characterResponseList = characterDataWrapper.getData().getResults().stream()
                .map(characterW -> {
                    CharacterResponse character = new CharacterResponse();
                    List<String> comics = characterW.getComics().getItems().stream()
                            .map(CharacterDataJson.Comic::getName)
                            .collect(Collectors.toList());
                    character.setName(characterW.getName());
                    character.setComics(comics);
                    character.setLastSync(characterW.getModified());
                    return character;
                })
                .collect(Collectors.toList());

        CharacterResponseWrapper characterResponseWrapper = new CharacterResponseWrapper();
        characterResponseWrapper.setCharacters(characterResponseList);

        return characterResponseWrapper;
    }

}
