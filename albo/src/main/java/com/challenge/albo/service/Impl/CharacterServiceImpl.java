package com.challenge.albo.service.Impl;

import com.challenge.albo.dto.CharacterResponseDTO;
import com.challenge.albo.service.CharacterService;
import com.challenge.albo.service.MarvelApiClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Override
    public CharacterResponseDTO getCharacterInformation() {
        try {
            String response = MarvelApiClient.sendRequest("characters");
            System.out.println(response);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
