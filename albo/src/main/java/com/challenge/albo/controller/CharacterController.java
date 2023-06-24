package com.challenge.albo.controller;

import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.dto.ComicResponse;
import com.challenge.albo.service.CharacterService;
import com.challenge.albo.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static com.challenge.albo.Util.MarvelConstants.*;

@RestController
@RequestMapping(API)
public class CharacterController {
    @Autowired
    CharacterService characterService;
    @Autowired
    ComicService comicService;

    @GetMapping(CHARACTER_PATH)
    public ResponseEntity<?> getCharacter() throws NoSuchAlgorithmException, IOException {
        CharacterResponseWrapper characterResponse = characterService.getCharacterInformation();
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }

    @GetMapping(CHARACTER_PATH + CHARACTER_BY_ID_PATH)
    public ResponseEntity<?> getComicsByIdCharacter(@PathVariable Long idCharacter) throws NoSuchAlgorithmException, IOException {
        ComicResponse comicResponse = comicService.getComicById(idCharacter);
        return new ResponseEntity<>(comicResponse, HttpStatus.OK);
    }


}
