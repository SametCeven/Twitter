package com.example.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends TwitterException {
    public EmailExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
