package com.challenge.albo.controller;

import com.challenge.albo.dto.CharacterResponseDTO;
import com.challenge.albo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class CharacterController {
    @Autowired
    CharacterService characterService;

    @GetMapping()
    public ResponseEntity<?> getAllMovements() throws NoSuchAlgorithmException, IOException {
        CharacterResponseDTO movementResponseDTOList = characterService.getCharacterInformation();
        return new ResponseEntity<>(movementResponseDTOList, HttpStatus.OK);
    }


}
