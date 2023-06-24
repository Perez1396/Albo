package com.challenge.albo.service;

import com.challenge.albo.dto.ComicResponse;
import com.challenge.albo.exception.MarvelException;

public interface ComicService {

    /**
     *
     * @param idCharacter a buscar en el endpoint
     * @return ComicResponse objeto con la información de los roles y nombres que participaron en ese comic
     */
    ComicResponse getComicById(Long idCharacter) throws MarvelException;
}
