package com.challenge.albo.service.Impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class MarvelApiClientTest {

    @Mock
    private HttpURLConnection connection;

    @Test
    public void testSendRequest() throws IOException, NoSuchAlgorithmException {
        // Arrange
        String endpoint = "characters";
        String expectedResponse = "{\"code\":200,\"status\":\"Ok\",\"data\":{\"offset\":0,\"limit\":20,\"total\":12,\"count\":12,\"results\":[{\"id\":22506,\"digitalId\":10949,\"issueNumber\":19,\"modified\":\"2015-10-27T16:38:23-0400\",\"ean\":\"\",\"pageCount\":32,\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/22506\",\"creators\":{\"available\":9,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/22506/creators\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/2133\",\"name\":\"TomBrevoort\",\"role\":\"editor\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/452\",\"name\":\"VirtualCalligr\",\"role\":\"letterer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/12581\",\"name\":\"ChrisEliopoulos\",\"role\":\"letterer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/1400\",\"name\":\"BongDazo\",\"role\":\"penciller\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/11765\",\"name\":\"ChristosGage\",\"role\":\"writer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/12983\",\"name\":\"DanSlott\",\"role\":\"writer\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/8706\",\"name\":\"JayDavidRamos\",\"role\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/500\",\"name\":\"ChristopherSotomayor\",\"role\":\"colorist\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/8584\",\"name\":\"HarveyTolibao\",\"role\":\"inker\"}],\"returned\":9},\"characters\":{\"available\":9,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/22506/characters\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1011334\",\"name\":\"3-DMan\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1010802\",\"name\":\"Ant-Man(EricO'Grady)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009165\",\"name\":\"Avengers\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009284\",\"name\":\"DumDumDugan\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1011490\",\"name\":\"HankPym\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1010821\",\"name\":\"Hardball\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009376\",\"name\":\"Jocasta\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1010818\",\"name\":\"Komodo(MelatiKusuma)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009737\",\"name\":\"Yellowjacket(HankPym)\"}],\"returned\":9}}]}}";

        URLWrapper urlWrapper = Mockito.mock(URLWrapper.class);
        when(urlWrapper.openConnection()).thenReturn(connection);
        when(connection.getInputStream()).thenReturn(Mockito.mock(InputStream.class));
        when(connection.getResponseCode()).thenReturn(200);
        when(connection.getInputStream()).thenReturn(new ByteArrayInputStream(expectedResponse.getBytes()));

        MarvelApiClient marvelApiClient = new MarvelApiClient();
        MarvelApiClient spyApiClient = spy(marvelApiClient);

        // Act
        String response = spyApiClient.sendRequest(endpoint);

        // Assert
        assertTrue(expectedResponse.contains("200"));
        assertTrue(response.contains("200"));
    }

    private static class URLWrapper {
        private final URL url;

        public URLWrapper(URL url) {
            this.url = url;
        }

        public HttpURLConnection openConnection() throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

}