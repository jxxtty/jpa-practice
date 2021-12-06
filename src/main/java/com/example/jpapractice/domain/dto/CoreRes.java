package com.example.jpapractice.domain.dto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CoreRes {
    private HttpStatus status;
    private String msg;
}
