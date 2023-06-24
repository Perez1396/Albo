package com.challenge.albo.service.Impl;

import com.challenge.albo.dto.CharacterDataJson;
import com.challenge.albo.dto.CharacterDataWrapper;
import com.challenge.albo.dto.CharacterResponseWrapper;
import com.challenge.albo.repository.CharacterRepository;
import com.challenge.albo.repository.ComicRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CharacterServiceImplTest {
    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private ComicRepository comicRepository;

    @Mock
    private MarvelApiClient marvelApiClient;

    @InjectMocks
    private CharacterServiceImpl characterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCharacterInformation_SuccessfulRequest_ReturnsCharacterResponseWrapper() {
        CharacterDataWrapper characterDataWrapper = new CharacterDataWrapper();
        CharacterDataWrapper.CharacterDataContainer dataContainer = new CharacterDataWrapper.CharacterDataContainer();
        characterDataWrapper.setData(dataContainer);
        CharacterDataJson characterDataJson = new CharacterDataJson();
        characterDataJson.setName("3-D Man");
        dataContainer.setResults(Collections.singletonList(characterDataJson));

        String mockResponse = "{\"code\":200,\"status\":\"Ok\",\"data\":{\"offset\":0,\"limit\":20,\"total\":12,\"count\":12,\"results\":[{\"id\":22506,\"digitalId\":10949,\"issueNumber\":19,\"modified\":\"2015-10-27T16:38:23-0400\",\"ean\":\"\",\"pageCount\":32,\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/22506\",\"creators\":{\"available\":9,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/22506/creators\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/2133\",\"name\":\"TomBrevoort\",\"role\":\"editor\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/452\",\"name\":\"VirtualCalligr\",\"role\":\"letterer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/12581\",\"name\":\"ChrisEliopoulos\",\"role\":\"letterer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/1400\",\"name\":\"BongDazo\",\"role\":\"penciller\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/11765\",\"name\":\"ChristosGage\",\"role\":\"writer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/12983\",\"name\":\"DanSlott\",\"role\":\"writer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/8706\",\"name\":\"JayDavidRamos\",\"role\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/500\",\"name\":\"ChristopherSotomayor\",\"role\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/8584\",\"name\":\"HarveyTolibao\",\"role\":\"inker\"}],\"returned\":9},\"characters\":{\"available\":9,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/22506/characters\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1011334\",\"name\":\"3-DMan\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1010802\",\"name\":\"Ant-Man(EricO'Grady)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009165\",\"name\":\"Avengers\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009284\",\"name\":\"DumDumDugan\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1011490\",\"name\":\"HankPym\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1010821\",\"name\":\"Hardball\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009376\",\"name\":\"Jocasta\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1010818\",\"name\":\"Komodo(MelatiKusuma)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009737\",\"name\":\"Yellowjacket(HankPym)\"}],\"returned\":9}}]}}";
        when(characterRepository.saveAll(anyList())).thenReturn(new ArrayList<>());
        when(comicRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        CharacterResponseWrapper response = characterService.getCharacterInformation();

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getCharacters().get(0).getName(), characterDataJson.getName());
        verify(characterRepository, times(1)).saveAll(anyList());
        verify(comicRepository, times(20)).saveAll(anyList());
    }

}