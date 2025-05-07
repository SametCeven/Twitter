package com.example.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class RetweetExistsException extends TwitterException {
    public RetweetExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
