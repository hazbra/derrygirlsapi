package com.derrygirls.exception;

public class QuoteNotFoundException extends RuntimeException{
    public QuoteNotFoundException(String exception) {
        super(exception);
    }

}
