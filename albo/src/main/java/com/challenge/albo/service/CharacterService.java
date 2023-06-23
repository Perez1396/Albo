package com.challenge.albo.service;

import com.challenge.albo.dto.CharacterResponseWrapper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface CharacterService {
    CharacterResponseWrapper getCharacterInformation() throws NoSuchAlgorithmException, IOException;
}
