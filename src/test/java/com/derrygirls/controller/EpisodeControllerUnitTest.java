package com.derrygirls.controller;

import com.derrygirls.entity.Character;
import com.derrygirls.entity.Episode;
import com.derrygirls.entity.Quote;
import com.derrygirls.exception.EpisodeNotFoundException;
import com.derrygirls.service.EpisodeService;
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
@WebMvcTest(EpisodeController.class)
public class EpisodeControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EpisodeService episodeService;


    @Test
    public void getAllEpisodes() throws Exception {
        mockMvc.perform(get("/derrygirls/episodes/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("[]"));

        verify(episodeService, times(1)).listEpisodes();
    }

    @Test
    public void getOneEpisode() throws Exception {
        Character character = new Character(1, "Clare Devlin");
        Character character2 = new Character(2, "Jenny Joyce");
        List<Character> characterList = new ArrayList<>();
        characterList.add(character);
        characterList.add(character2);

        Quote quote = new Quote(1, "A funny quote", 1, 9);
        Quote quote2 = new Quote(1, "A funnier quote", 2, 9);
        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote);
        quoteList.add(quote2);

        Episode episode = new Episode(1, "Episode One", "Funny stuff happens", 4, characterList, quoteList);
        when(episodeService.findEpisode(1)).thenReturn(episode);

        mockMvc.perform(get("/derrygirls/episode/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("Funny stuff happens")))
                .andExpect(jsonPath("$.seasonId", is(4)))
                .andExpect(jsonPath("$.characters[0].name", is("Clare Devlin")))
                .andExpect(jsonPath("$.characters[1].name", is("Jenny Joyce")))
                .andExpect(jsonPath("$.quotes[0].description", is("A funny quote")))
                .andExpect(jsonPath("$.quotes[1].description", is("A funnier quote")));

    }

    @Test
    public void getOneEpisodeException() throws Exception {

        when(episodeService.findEpisode(75)).thenThrow(new EpisodeNotFoundException("Episode Not Found"));

        mockMvc.perform(get("/derrygirls/episode/75" ))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Episode Not Found"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));

    }
}
