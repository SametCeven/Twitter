package com.example.twitter.service;

import com.example.twitter.dto.RetweetRequestDto;
import com.example.twitter.dto.RetweetResponseDto;

public interface RetweetService {
    RetweetResponseDto save(RetweetRequestDto retweetRequestDto, String username);
    void delete(Long id);
}
