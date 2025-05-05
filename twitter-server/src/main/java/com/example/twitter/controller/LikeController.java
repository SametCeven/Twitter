package com.example.twitter.controller;

import com.example.twitter.dto.LikeRequestDto;
import com.example.twitter.dto.LikeResponseDto;
import com.example.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping("/like")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto like(
            @RequestBody LikeRequestDto likeRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return likeService.save(likeRequestDto, username);
    }

    @PostMapping("/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto dislike(
            @RequestBody LikeRequestDto likeRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return likeService.remove(likeRequestDto, username);
    }



}
