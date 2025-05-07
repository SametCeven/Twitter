package com.example.twitter.service;

import com.example.twitter.dto.CommentRequestDto;
import com.example.twitter.dto.CommentResponseDto;


public interface CommentService {
    CommentResponseDto save(CommentRequestDto commentRequestDto, String username);
    CommentResponseDto put(Long id, CommentRequestDto commentRequestDto, String username);
    CommentResponseDto patch(Long id, CommentRequestDto commentRequestDto, String username);
    void delete(Long id);
}
