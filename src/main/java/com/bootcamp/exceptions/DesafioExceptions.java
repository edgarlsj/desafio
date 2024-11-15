package com.bootcamp.exceptions;

import org.springframework.http.ResponseEntity;

public class DesafioExceptions extends Exception {



    public DesafioExceptions(String message, ResponseEntity.BodyBuilder status) {
        {

        }
    }

    public DesafioExceptions(String message, int status) {
        {

        }
    }
}
