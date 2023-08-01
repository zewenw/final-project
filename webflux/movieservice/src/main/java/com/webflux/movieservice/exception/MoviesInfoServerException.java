package com.webflux.movieservice.exception;

public class MoviesInfoServerException extends RuntimeException{

    private String message;

    public MoviesInfoServerException(String message) {
        super(message);
        this.message = message;
    }
}
