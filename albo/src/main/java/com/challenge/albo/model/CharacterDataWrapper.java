package com.challenge.albo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDataWrapper {
    private CharacterDataContainer data;

    @Getter
    @Setter
    public static class CharacterDataContainer {
        private List<Character> results;
    }
}
