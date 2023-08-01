package com.webflux.movieservice.exception;

public class ReviewsServerException extends RuntimeException{

    private String message;

    public ReviewsServerException(String message) {
        super(message);
        this.message = message;
    }
}
