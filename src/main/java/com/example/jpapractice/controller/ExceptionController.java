package com.example.jpapractice.controller;

import com.example.jpapractice.domain.dto.ExceptionRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({RuntimeException.class, IllegalArgumentException.class})
    public ResponseEntity<ExceptionRes> badRequestException(Exception ex) {
        ExceptionRes res = new ExceptionRes();
        res.setStatus(HttpStatus.BAD_REQUEST);
        res.setMsg(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
