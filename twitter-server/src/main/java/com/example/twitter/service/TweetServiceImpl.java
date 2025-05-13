package com.example.twitter.service;

import com.example.twitter.dto.TweetRequestDto;
import com.example.twitter.dto.TweetResponseDto;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.TweetNotFoundException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{

    private static final Logger logger = LoggerFactory.getLogger(TweetServiceImpl.class);

    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private DtoMapping dtoMapping;

    @Autowired
    public TweetServiceImpl(DtoMapping dtoMapping, TweetRepository tweetRepository, UserRepository userRepository){
        this.dtoMapping = dtoMapping;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TweetResponseDto save(TweetRequestDto tweetRequestDto, String username) {

        logger.debug("Saving tweet with username : {}", username);

        Tweet tweet = new Tweet();
        tweet.setTweetText(tweetRequestDto.getTweetText());
        tweet.setCreatedDate(tweetRequestDto.getCreatedDate());
        tweet.setPicture(tweetRequestDto.getPicture());

        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()->{
                    logger.error("User not found with username : {}", username);
                    return new UserNotFoundException("User not found");
                });

        user.addTweet(tweet);
        tweet.setUser(user);

        tweetRepository.save(tweet);

        logger.info("Tweet saved successfully");

        return dtoMapping.MappingTweetToTweetResponseDto(tweet);
    }

    @Override
    public List<TweetResponseDto> getAll() {
        return tweetRepository
                .findAll()
                .stream()
                .map((tweet)-> dtoMapping.MappingTweetToTweetResponseDto(tweet))
                .toList();
    }

    @Override
    public List<TweetResponseDto> getByUserId(String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()->new UserNotFoundException("User with username:" + username + " does not exist"));
        List<Tweet> tweets = tweetRepository.getByUserId(user.getId());
        List<TweetResponseDto> responseDtos = new ArrayList<>();
        for(Tweet tweet:tweets){
            responseDtos.add(dtoMapping.MappingTweetToTweetResponseDto(tweet));
        }
        return responseDtos;
    }

    @Override
    public TweetResponseDto getById(Long id) {
        Tweet tweet = tweetRepository
                .findById(id)
                .orElseThrow(()->new TweetNotFoundException("Tweet with " + id + " not found."));
        return dtoMapping.MappingTweetToTweetResponseDto(tweet);
    }

    @Override
    public TweetResponseDto put(Long id, TweetRequestDto tweetRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);

        Tweet tweet = dtoMapping.MappingTweetRequestToTweet(tweetRequestDto);

        if (tweetOptional.isPresent()){
            tweet.setId(id);
            tweet.setUser(user);
            tweetRepository.save(tweet);
            return dtoMapping.MappingTweetToTweetResponseDto(tweet);
        }

        user.addTweet(tweet);
        tweet.setUser(user);
        tweetRepository.save(tweet);
        return dtoMapping.MappingTweetToTweetResponseDto(tweet);

    }

    @Override
    public TweetResponseDto patch(Long id, TweetRequestDto tweetRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        Tweet tweet = tweetRepository
                .findById(id)
                .orElseThrow(()->new TweetNotFoundException("Tweet with " + id + " not found."));

        if(tweetRequestDto.getTweetText() != null) tweet.setTweetText(tweetRequestDto.getTweetText());
        if(tweetRequestDto.getCreatedDate() != null) tweet.setCreatedDate(tweetRequestDto.getCreatedDate());
        if(tweetRequestDto.getPicture() != null) tweet.setPicture(tweetRequestDto.getPicture());
        tweetRepository.save(tweet);
        return dtoMapping.MappingTweetToTweetResponseDto(tweet);
    }

    @Override
    public void delete(Long id) {
        Tweet tweet = tweetRepository
                .findById(id)
                .orElseThrow(()-> new TweetNotFoundException("Tweet with " + id + " not found."));
        tweetRepository.deleteById(id);
    }
}
