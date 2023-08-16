package com.finalproject.user.exception;

public class UserException extends RuntimeException {

    private String message;

    public UserException(String s) {
        super(s);
        this.message = s;
    }
}
