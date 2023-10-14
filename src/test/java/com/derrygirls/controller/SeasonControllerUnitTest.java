package com.derrygirls.controller;

import com.derrygirls.entity.Season;
import com.derrygirls.exception.SeasonNotFoundException;
import com.derrygirls.service.SeasonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SeasonController.class)
public class SeasonControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SeasonService seasonService;

    @Test
    public void getAllSeasons() throws Exception {
        mockMvc.perform(get("/derrygirls/seasons/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("[]"));

        verify(seasonService, times(1)).listSeasons();
    }

    @Test
    public void getOneSeason() throws Exception {
        Season season = new Season(1,"Season One");
        when(seasonService.findSeason(1)).thenReturn(season);

        mockMvc.perform(get("/derrygirls/season/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Season One")));

    }

    @Test
    public void getOneEpisodeException() throws Exception {

        when(seasonService.findSeason(75)).thenThrow(new SeasonNotFoundException("Season Not Found"));

        mockMvc.perform(get("/derrygirls/season/75" ))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Season 75 does not exist. You might want to think about wising up."))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));

    }
}
