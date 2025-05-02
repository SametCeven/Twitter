package com.example.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends TwitterException {
    public CommentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
