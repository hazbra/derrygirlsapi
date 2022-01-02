package com.derrygirls.service;

import com.derrygirls.entity.Quote;

import java.util.List;

public interface QuoteService {
    List<Quote> listQuotes();
    Quote findQuote(long id);
    Long count();
}
