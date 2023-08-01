package com.webflux.movieinfoservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleRequestBodyError(WebExchangeBindException ex){
        log.error("Exception caught in handleRequestBodyError :  {} " ,ex.getMessage(),  ex);
        var error = ex.getBindingResult().getAllErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(item -> Objects.requireNonNull(item.getDefaultMessage()).isEmpty() ? item.getObjectName() + "param error" : item.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining(","));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

//    @ExceptionHandler(MovieInfoNotfoundException.class)
//    public ResponseEntity<String> handleMovieInfoNotfoundException(MovieInfoNotfoundException ex){
//        log.error("Exception caught in handleMovieInfoNotfoundException :  {} " ,ex.getMessage(),  ex);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
}
