package com.example.twitter.service;

import com.example.twitter.dto.TweetRequestDto;
import com.example.twitter.dto.TweetResponseDto;

import java.util.List;

public interface TweetService {
    TweetResponseDto save(TweetRequestDto tweetRequestDto, String username);
    List<TweetResponseDto> getByUserId(String username);
    TweetResponseDto getById(Long id);
    TweetResponseDto put(Long id, TweetRequestDto tweetRequestDto, String username);
    TweetResponseDto patch(Long id, TweetRequestDto tweetRequestDto, String username);
    void delete(Long id);

}
