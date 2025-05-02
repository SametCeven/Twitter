package com.example.twitter.service;

import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> getALl();
    Comment getById(Long id);
    Comment save(Comment comment, Tweet tweet, User user);
    Comment put(Long id, Comment comment, Tweet tweet, User user);
    Comment patch(Long id, Comment comment);
    void delete(Long id);
}
