package com.example.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class LikeExistsException extends TwitterException {
    public LikeExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
