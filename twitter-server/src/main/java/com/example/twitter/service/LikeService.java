package com.example.twitter.service;

import com.example.twitter.dto.LikeCommentRequestDto;
import com.example.twitter.dto.LikeCommentResponseDto;
import com.example.twitter.dto.LikeTweetRequestDto;
import com.example.twitter.dto.LikeTweetResponseDto;

public interface LikeService {
    LikeTweetResponseDto saveTweet(LikeTweetRequestDto likeTweetRequestDto, String username);
    LikeTweetResponseDto removeTweet(LikeTweetRequestDto likeTweetRequestDto, String username);
    Boolean isTweetLiked(Long tweetId, String username);

    LikeCommentResponseDto saveComment(LikeCommentRequestDto likeCommentRequestDto, String username);
    LikeCommentResponseDto removeComment(LikeCommentRequestDto likeCommentRequestDto, String username);

}
