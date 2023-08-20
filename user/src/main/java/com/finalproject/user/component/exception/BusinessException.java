package com.finalproject.user.component.exception;

public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException(String s) {
        super(s);
        this.message = s;
    }
}
