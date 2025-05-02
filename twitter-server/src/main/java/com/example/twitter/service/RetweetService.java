package com.example.twitter.service;

import com.example.twitter.entity.Retweet;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

import java.util.List;

public interface RetweetService {
    List<Retweet> getALl();
    Retweet getById(Long id);
    Retweet save(Retweet retweet, Tweet tweet, User user);
    Retweet put(Long id, Retweet retweet, Tweet tweet, User user);
    Retweet patch(Long id, Retweet retweet);
    void delete(Long id);
}
