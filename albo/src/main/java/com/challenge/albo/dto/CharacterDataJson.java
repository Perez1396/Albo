package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CharacterDataJson {
    private String name;
    private Date modified;
    private Comics comics;

    @Getter
    @Setter
    public static class Comics {
        private List<Comic> items;
    }

    @Getter
    @Setter
    public static class Comic {
        private String name;
    }
}