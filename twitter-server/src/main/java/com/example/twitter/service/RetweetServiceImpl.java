package com.example.twitter.service;

import com.example.twitter.entity.Retweet;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.RetweetNotFoundException;
import com.example.twitter.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetweetServiceImpl implements RetweetService{

    private RetweetRepository retweetRepository;

    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository){
        this.retweetRepository = retweetRepository;
    }

    @Override
    public List<Retweet> getALl() {
        return retweetRepository.findAll();
    }

    @Override
    public Retweet getById(Long id) {
        return retweetRepository
                .findById(id)
                .orElseThrow(()->new RetweetNotFoundException("Retweet with " + id + " not found."));
    }

    @Override
    public Retweet save(Retweet retweet, Tweet tweet, User user) {
        user.addRetweet(retweet);
        tweet.addRetweet(retweet);
        return retweetRepository.save(retweet);
    }

    @Override
    public Retweet put(Long id, Retweet retweet,Tweet tweet, User user) {
        Optional<Retweet> retweetOptional = retweetRepository.findById(id);
        if(retweetOptional.isPresent()){
            retweet.setId(id);
            return retweetRepository.save(retweet);
        }
        user.addRetweet(retweet);
        tweet.addRetweet(retweet);
        return retweetRepository.save(retweet);
    }

    @Override
    public Retweet patch(Long id, Retweet retweet) {
        return retweet;
    }

    @Override
    public void delete(Long id) {
        retweetRepository.deleteById(id);
    }
}
