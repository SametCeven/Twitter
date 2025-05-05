package com.example.twitter.service;

import com.example.twitter.dto.LikeRequestDto;
import com.example.twitter.dto.LikeResponseDto;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Like;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

import java.util.List;

public interface LikeService {
    LikeResponseDto save(LikeRequestDto likeRequestDto, String username);
    LikeResponseDto remove(LikeRequestDto likeRequestDto, String username);
}
