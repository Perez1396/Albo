package com.challenge.albo.mapper;

import com.challenge.albo.dto.ComicDataJson;
import com.challenge.albo.dto.ComicDataWrapper;
import com.challenge.albo.dto.ComicResponse;

import java.util.*;
import java.util.stream.Collectors;

import static com.challenge.albo.Util.MarvelConstants.*;

public class ComicMapper {
    public static ComicResponse buildComicCreatorType(ComicDataWrapper comicDataWrapper) {
        ComicResponse comicResponse = new ComicResponse();

        List<String> desiredRoles = Arrays.asList(EDITOR, WRITER, COLORIST);

        Map<String, Set<String>> namesByRole = comicDataWrapper.getData().getResults().stream()
                .filter(comic -> comic.getCreators() != null && comic.getCreators().getItems() != null)
                .flatMap(comic -> comic.getCreators().getItems().stream())
                .filter(creator -> desiredRoles.contains(creator.getRole()))
                .collect(Collectors.groupingBy(ComicDataJson.Creator::getRole, Collectors.mapping(ComicDataJson.Creator::getName, Collectors.toSet())));

        List<String> editor = new ArrayList<>(namesByRole.get(EDITOR));
        List<String> writer = new ArrayList<>(namesByRole.get(WRITER));
        List<String> colorist = new ArrayList<>(namesByRole.get(COLORIST));

        comicResponse.setColorists(colorist);
        comicResponse.setEditors(editor);
        comicResponse.setWriters(writer);
        comicResponse.setLastSync(new Date());

        return comicResponse;
    }
}
