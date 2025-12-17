package com.example.handler;

import com.example.exception.AuthFailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthFailException.class)
    public ResponseEntity<Object> authFailExceptionHandler(AuthFailException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(getMap(HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }

    private Map<String, Object> getMap(HttpStatus status, String mesaj) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("error", mesaj);
        return map;
    }

}
