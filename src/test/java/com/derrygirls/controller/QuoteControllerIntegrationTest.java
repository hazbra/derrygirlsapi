package com.derrygirls.controller;

import com.derrygirls.entity.Quote;
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
public class QuoteControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllQuotes() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/quotes/", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(12, response.getBody().size());
    }

    @Test
    public void getQuote3() throws Exception {
        ResponseEntity<Quote> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/quote/3/", Quote.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertEquals(3, response.getBody().getId());
        assertEquals("Sure, what's a pair of knickers between cousins?", response.getBody().getDescription());
    }

    @Test
    public void shouldReturnQuoteNotFound() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/derrygirls/quote/33/", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}

