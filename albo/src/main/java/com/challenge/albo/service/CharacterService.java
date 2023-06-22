package com.challenge.albo.service;

import com.challenge.albo.dto.CharacterResponseDTO;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface CharacterService {

    CharacterResponseDTO getCharacterInformation() throws NoSuchAlgorithmException, IOException;

}
