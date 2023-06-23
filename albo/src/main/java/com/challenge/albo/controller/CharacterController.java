package com.challenge.albo.controller;

import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @GetMapping()
    public ResponseEntity<?> getCharacter() throws NoSuchAlgorithmException, IOException {
        CharacterResponseWrapper characterResponse = characterService.getCharacterInformation();
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }


}
