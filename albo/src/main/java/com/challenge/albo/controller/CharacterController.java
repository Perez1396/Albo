package com.challenge.albo.controller;

import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.dto.ComicResponse;
import com.challenge.albo.exception.MarvelException;
import com.challenge.albo.service.CharacterService;
import com.challenge.albo.service.ComicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static com.challenge.albo.Util.MarvelConstants.*;

@RestController
@RequestMapping(API)
@Api(tags = "Marvel main controller")
public class CharacterController {
    @Autowired
    CharacterService characterService;
    @Autowired
    ComicService comicService;

    @GetMapping(CHARACTER_PATH)
    @ApiOperation(value = "Retrieve the name of the comics given a character.")
    public ResponseEntity<?> getCharacter() throws NoSuchAlgorithmException, IOException, MarvelException {
        CharacterResponseWrapper characterResponse = characterService.getCharacterInformation();
        return new ResponseEntity<>(characterResponse, HttpStatus.OK);
    }

    @GetMapping(CHARACTER_PATH + CHARACTER_BY_ID_PATH)
    @ApiOperation(value = "Retrieve the name of the editors, writers and colorist of the comics given a character.")
    public ResponseEntity<?> getComicsByIdCharacter(@ApiParam(value = "Id of the character.", required = true) @PathVariable Long idCharacter) throws NoSuchAlgorithmException, IOException, MarvelException {
        ComicResponse comicResponse = comicService.getComicById(idCharacter);
        return new ResponseEntity<>(comicResponse, HttpStatus.OK);
    }


}
