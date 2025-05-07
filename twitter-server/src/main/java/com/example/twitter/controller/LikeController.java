package com.example.twitter.controller;

import com.example.twitter.dto.LikeCommentRequestDto;
import com.example.twitter.dto.LikeCommentResponseDto;
import com.example.twitter.dto.LikeTweetRequestDto;
import com.example.twitter.dto.LikeTweetResponseDto;
import com.example.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping("/like/tweet")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeTweetResponseDto likeTweet(
            @RequestBody LikeTweetRequestDto likeTweetRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return likeService.saveTweet(likeTweetRequestDto, username);
    }

    @PostMapping("/dislike/tweet")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeTweetResponseDto dislikeTweet(
            @RequestBody LikeTweetRequestDto likeTweetRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return likeService.removeTweet(likeTweetRequestDto, username);
    }

    @PostMapping("/like/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeCommentResponseDto likeComment(
            @RequestBody LikeCommentRequestDto likeCommentRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return likeService.saveComment(likeCommentRequestDto, username);
    }

    @PostMapping("/dislike/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeCommentResponseDto dislikeComment(
            @RequestBody LikeCommentRequestDto likeCommentRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return likeService.removeComment(likeCommentRequestDto, username);
    }



}
