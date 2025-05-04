package com.example.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameExistsException extends TwitterException {
    public UsernameExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
