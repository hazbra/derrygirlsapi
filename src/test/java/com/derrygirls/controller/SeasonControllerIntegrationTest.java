package com.derrygirls.controller;

import com.derrygirls.entity.Season;
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
public class SeasonControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String uriBase = "http://localhost:";

    @Test
    public void getAllSeasons() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity(uriBase + port + "/derrygirls/seasons/", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void getSeason2() throws Exception {
        ResponseEntity<Season> response =
                this.restTemplate.getForEntity(uriBase + port + "/derrygirls/season/2/", Season.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(2, response.getBody().getId());
        assertEquals("Season Two", response.getBody().getName());
        assertEquals(6, response.getBody().getEpisodes().size());

    }

    @Test
    public void shouldReturnSeasonNotFound() throws Exception {
        ResponseEntity<Season> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/season/33/", Season.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}

