package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComicDataWrapper {
    private ComicDataContainer data;

    @Getter
    @Setter
    public static class ComicDataContainer {
        private List<ComicDataJson> results;
    }
}
