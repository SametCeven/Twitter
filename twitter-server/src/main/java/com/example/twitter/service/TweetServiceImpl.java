package com.example.twitter.service;

import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.TweetNotFoundException;
import com.example.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository){
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<Tweet> getALl() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet getById(Long id) {
        return tweetRepository
                .findById(id)
                .orElseThrow(()->new TweetNotFoundException("Tweet with " + id + " not found."));
    }

    @Override
    public Tweet save(Tweet tweet, User user) {
        user.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet put(Long id, Tweet tweet, User user) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if(tweetOptional.isPresent()){
            tweet.setId(id);
            return tweetRepository.save(tweet);
        }
        user.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet patch(Long id, Tweet tweet) {
        Tweet tweetOptional = tweetRepository
                .findById(id)
                .orElseThrow(()->new TweetNotFoundException("Tweet with " + id + " not found."));
        if(tweet.getTweetText() != null) tweetOptional.setTweetText(tweet.getTweetText());
        if(tweet.getCreatedDate() != null) tweetOptional.setCreatedDate(tweet.getCreatedDate());
        if(tweet.getPicture() != null) tweetOptional.setPicture(tweet.getPicture());
        return tweetRepository.save(tweetOptional);
    }

    @Override
    public void delete(Long id) {
        tweetRepository.deleteById(id);
    }
}
