package com.derrygirls.controller;

import com.derrygirls.entity.Episode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EpisodeControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllEpisodes() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/episodes/", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(12, response.getBody().size());
    }

    @Test
    public void getEpisode3() throws Exception {
        ResponseEntity<Episode> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/episode/3/", Episode.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(3, response.getBody().getId());
        assertEquals("Episode Three", response.getBody().getName());
        assertEquals(1, response.getBody().getSeasonId());
        assertEquals("The girls are tense about a big exam and naturally jump at a dubious opportunity to get out of it, especially as it involves spending time with the beautiful Father Peter.", response.getBody().getDescription());
        assertEquals(7, response.getBody().getCharacters().size());
        assertEquals("Erin Quinn", response.getBody().getCharacters().get(0).getName());
        assertEquals(8, response.getBody().getQuotes().get(1).getId());

    }

    @Test
    public void shouldReturnEpisodeNotFound() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/episode/33/", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}

