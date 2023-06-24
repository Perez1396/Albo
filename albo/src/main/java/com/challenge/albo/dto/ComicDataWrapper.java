package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComicDataWrapper {
    private ComicDataContainer data;

    @Override
    public String toString() {
        return "ComicDataWrapper{" +
                "data=" + data +
                '}';
    }

    @Getter
    @Setter
    public static class ComicDataContainer {
        private List<ComicDataJson> results;
    }
}
