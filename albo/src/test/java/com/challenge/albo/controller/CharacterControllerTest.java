package com.challenge.albo.controller;

import com.challenge.albo.dto.CharacterResponse;
import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.dto.ComicResponse;
import com.challenge.albo.service.CharacterService;
import com.challenge.albo.service.ComicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;

@WebMvcTest(CharacterController.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @MockBean
    private ComicService comicService;

    @Test
    public void testGetCharacter() throws Exception {
        // Arrange
        CharacterResponseWrapper mockResponse = new CharacterResponseWrapper();
        CharacterResponse characterResponse = new CharacterResponse();
        characterResponse.setLastSync(new Date());
        characterResponse.setName("Antman");
        mockResponse.setLastSync(new Date());
        mockResponse.setCharacters(Arrays.asList(characterResponse));

        Mockito.when(characterService.getCharacterInformation()).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/public/character")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetComicsByIdCharacter() throws Exception {
        // Arrange
        Long idCharacter = 1L;
        ComicResponse mockResponse = new ComicResponse();

        Mockito.when(comicService.getComicById(idCharacter)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/public/character/{idCharacter}/comics", idCharacter)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify the method calls
        Mockito.verify(comicService).getComicById(idCharacter);
    }
}