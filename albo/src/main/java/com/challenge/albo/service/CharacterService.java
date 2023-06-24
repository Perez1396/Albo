package com.challenge.albo.service;

import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.exception.MarvelException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface CharacterService {

    /**
     *
     * @return CharacterResponseWrapper objeto el cual contiene la estructura con la informacion de personaes por comic
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    CharacterResponseWrapper getCharacterInformation() throws NoSuchAlgorithmException, IOException, MarvelException;
}
