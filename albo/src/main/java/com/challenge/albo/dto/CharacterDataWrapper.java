package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDataWrapper {
    private CharacterDataContainer data;

    @Override
    public String toString() {
        return "CharacterDataWrapper{" +
                "data=" + data +
                '}';
    }

    @Getter
    @Setter
    public static class CharacterDataContainer {
        private List<CharacterDataJson> results;
    }
}
