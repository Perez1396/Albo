package com.challenge.albo.mapper;

import com.challenge.albo.dto.CharacterDataWrapper;
import com.challenge.albo.dto.CharacterResponse;
import com.challenge.albo.dto.CharacterResponseWrapper;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {
    public static CharacterResponseWrapper buildCharacter(CharacterDataWrapper characterDataWrapper) {
        CharacterResponseWrapper characterResponseWrapper = new CharacterResponseWrapper();
        List<CharacterResponse> characterResponseList = new ArrayList<>();
        characterDataWrapper.getData().getResults().forEach(characterW -> {
            CharacterResponse character = new CharacterResponse();
            List<String> comics = new ArrayList<>();
            character.setName(characterW.getName());
            characterW
                    .getComics()
                    .getItems()
                    .forEach(comic -> comics.add(comic.getName()));
            character.setComics(comics);
            character.setLastSync(characterW.getModified());
            characterResponseList.add(character);
            characterResponseWrapper.setCharacters(characterResponseList);
        });
        return characterResponseWrapper;
    }

}
