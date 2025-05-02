package com.example.twitter.service;

import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

import java.util.List;

public interface TweetService {
    List<Tweet> getALl();
    Tweet getById(Long id);
    Tweet save(Tweet tweet, User user);
    Tweet put(Long id, Tweet tweet, User user);
    Tweet patch(Long id, Tweet tweet);
    void delete(Long id);

}
