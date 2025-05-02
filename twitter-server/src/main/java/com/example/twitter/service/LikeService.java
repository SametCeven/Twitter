package com.example.twitter.service;

import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Like;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

import java.util.List;

public interface LikeService {
    List<Like> getALl();
    Like getById(Long id);
    Like save(Like like, Comment comment, Tweet tweet, User user);
    Like put(Long id, Like like, Comment comment, Tweet tweet, User user);
    Like patch(Long id, Like like);
    void delete(Long id);

}
