package com.derrygirls.controller;

import com.derrygirls.entity.Quote;
import com.derrygirls.exception.QuoteNotFoundException;
import com.derrygirls.service.QuoteService;
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
@WebMvcTest(QuoteController.class)
public class QuoteControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    QuoteService quoteService;

    @Test
    public void getAllQuotes() throws Exception {
        mockMvc.perform(get("/derrygirls/quotes/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("[]"));

        verify(quoteService, times(1)).listQuotes();
    }

    @Test
    public void getOneQuote() throws Exception {

        Quote quote = new Quote(1, "Is that you Clare Devlin?", 5, 9);
        when(quoteService.findQuote(1)).thenReturn(quote);

        mockMvc.perform(get("/derrygirls/quote/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("Is that you Clare Devlin?")))
                .andExpect(jsonPath("$.characterId", is(5)))
                .andExpect(jsonPath("$.episodeId", is(9)));
    }

    @Test
    public void getOneQuoteException() throws Exception {

        when(quoteService.findQuote(75)).thenThrow(new QuoteNotFoundException("Quote Not Found"));

        mockMvc.perform(get("/derrygirls/quote/75" ))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Quote 75 does not exist. You might want to think about wising up."))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }
}
