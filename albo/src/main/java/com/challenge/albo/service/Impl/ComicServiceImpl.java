package com.challenge.albo.service.Impl;

import com.challenge.albo.dto.ComicDataWrapper;
import com.challenge.albo.dto.ComicResponse;
import com.challenge.albo.exception.MarvelException;
import com.challenge.albo.mapper.ComicMapper;
import com.challenge.albo.model.Creator;
import com.challenge.albo.model.CreatorType;
import com.challenge.albo.repository.CreatorRepository;
import com.challenge.albo.service.ComicService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static com.challenge.albo.Util.MarvelConstants.*;

@Slf4j
@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    CreatorRepository creatorRepository;
    private static Gson gson;

    @Override
    public ComicResponse getComicById(Long idCharacter) throws MarvelException {
        ComicDataWrapper comic = new ComicDataWrapper();
        gson = new GsonBuilder().create();
        String endpoint = CHARACTERS + "/" + idCharacter + "/" + COMICS;
        try {
            String response = MarvelApiClient.sendRequest(endpoint);
            comic = gson.fromJson(response, ComicDataWrapper.class);
            log.info("Json después de consumir la API: {}", comic.toString());
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new MarvelException(SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return mapWrapperToResponse(comic);
    }

    private ComicResponse mapWrapperToResponse(ComicDataWrapper comic) {
        ComicResponse comicResponse = ComicMapper.buildComicCreatorType(comic);
        syncCreatorWithDb(comicResponse);
        log.info("Objeto después de mapear el json: {}", comicResponse);
        return comicResponse;
    }

    private void syncCreatorWithDb(ComicResponse comicResponse) {
        List<String> writers = comicResponse.getWriters();
        List<String> colorists = comicResponse.getColorists();
        List<String> editors = comicResponse.getEditors();

        List<Creator> creators = new ArrayList<>();

        writers.forEach(w -> creators.add(createCreator(w, CreatorType.WRITER)));
        colorists.forEach(c -> creators.add(createCreator(c, CreatorType.COLORIST)));
        editors.forEach(e -> creators.add(createCreator(e, CreatorType.EDITOR)));
        log.info("Entidad creator para sincronizar en db: {}", creators);
        creatorRepository.saveAll(creators);
    }

    private Creator createCreator(String name, CreatorType type) {
        Creator creator = new Creator();
        creator.setType(type);
        creator.setName(name);
        return creator;
    }
}
