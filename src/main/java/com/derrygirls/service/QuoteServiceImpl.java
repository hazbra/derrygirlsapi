package com.derrygirls.service;

import com.derrygirls.entity.Quote;
import com.derrygirls.exception.QuoteNotFoundException;
import com.derrygirls.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public List<Quote> listQuotes() {
        return (List<Quote>) quoteRepository.findAll();
    }

    @Override
    public Quote findQuote(long id) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);

        if(optionalQuote.isPresent())
            return optionalQuote.get();
        else
            throw new QuoteNotFoundException("Quote Not Found");
    }

    public Long count() {
        return quoteRepository.count();
    }
}
