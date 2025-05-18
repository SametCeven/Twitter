package com.example.twitter.service;

import com.example.twitter.dto.RetweetCommentRequestDto;
import com.example.twitter.dto.RetweetCommentResponseDto;
import com.example.twitter.dto.RetweetTweetRequestDto;
import com.example.twitter.dto.RetweetTweetResponseDto;

public interface RetweetService {
    RetweetTweetResponseDto saveTweet(RetweetTweetRequestDto retweetTweetRequestDto, String username);
    void deleteTweet(Long tweetId, String username);
    Boolean isRetweeted(Long tweetId, String username);

    RetweetCommentResponseDto saveComment(RetweetCommentRequestDto retweetCommentRequestDto, String username);
    void deleteComment(Long id);
}
