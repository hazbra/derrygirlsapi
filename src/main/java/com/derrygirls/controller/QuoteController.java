package com.derrygirls.controller;

import com.derrygirls.entity.Quote;
import com.derrygirls.exception.QuoteNotFoundException;
import com.derrygirls.service.QuoteService;
import com.derrygirls.utils.DerryGirlsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/derrygirls")
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    private QuoteService quoteService;

    @Value("${does.not.exist}")
    public String defaultMessage;

    @Autowired
    public void setQuoteService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        logger.info("Listing all quotes");
        List<Quote> list = quoteService.listQuotes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/quote/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable("id") long id) {
        try {
            logger.info("Finding quote with id {}", id);
            return new ResponseEntity<>(quoteService.findQuote(id), HttpStatus.OK);
        } catch (QuoteNotFoundException exception) {
            logger.error("Something went wrong with quote id {}", id, exception);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DerryGirlsUtils.addExceptionMessage(id, defaultMessage, "Quote"));
        }
    }
}
