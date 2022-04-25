package com.derrygirls.controller;

import com.derrygirls.entity.Character;
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
public class CharacterControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllCharacters() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/characters/", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(7, response.getBody().size());
    }

    @Test
    public void getCharacter3() throws Exception {
        ResponseEntity<Character> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/character/3/", Character.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(3, response.getBody().getId());
        assertEquals("Clare Devlin", response.getBody().getName());
        assertEquals("Calm down? We're still on William of Orange, Michelle! We haven't so much looked at the famine!", response.getBody().getQuotes().get(0).getDescription());

    }

    @Test
    public void shouldReturnCharacterNotFound() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/character/33/", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}

