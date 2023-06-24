package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ComicDataJson {
    private String name;
    private Creators creators;

    @Getter
    @Setter
    public static class Creators {
        private List<Creator> items;
    }

    @Getter
    @Setter
    public static class Creator {
        private String name;
        private String role;
    }
}
