package com.challenge.albo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character {
    private String name;
    private String description;
    private Thumbnail thumbnail;

    // Getters and setters for the attributes

    @Getter
    @Setter
    public static class Thumbnail {
        private String path;
        private String extension;

        // Getters and setters for the attributes
    }
}