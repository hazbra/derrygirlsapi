package com.derrygirls.controller;

import com.derrygirls.entity.Character;
import com.derrygirls.entity.Episode;
import com.derrygirls.exception.CharacterNotFoundException;
import com.derrygirls.service.CharacterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CharacterService characterService;


    @Test
    public void getAllCharacters() throws Exception {
        mockMvc.perform(get("/derrygirls/characters/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("[]"));

        verify(characterService, times(1)).listCharacters();
    }

    @Test
    public void getOneCharacter() throws Exception {
        Episode episode = new Episode(1, "Episode One", "Funny stuff happens", 4);
        Episode episode2 = new Episode(2, "Episode Two", "Funnier stuff happens", 4);
        List<Episode> episodeList = new ArrayList<>();
        episodeList.add(episode);
        episodeList.add(episode2);
        Character character = new Character(1, "Clare Devlin", episodeList);
        when(characterService.findCharacter(1)).thenReturn(character);

        mockMvc.perform(get("/derrygirls/character/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Clare Devlin")));
    }

    @Test
    public void getOneCharacterException() throws Exception {

        when(characterService.findCharacter(75)).thenThrow(new CharacterNotFoundException("Character Not Found"));

        mockMvc.perform(get("/derrygirls/character/75" ))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Character Not Found"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }
}
