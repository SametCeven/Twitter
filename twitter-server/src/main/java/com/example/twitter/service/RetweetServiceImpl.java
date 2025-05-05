package com.example.twitter.service;

import com.example.twitter.dto.RetweetRequestDto;
import com.example.twitter.dto.RetweetResponseDto;
import com.example.twitter.entity.Retweet;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.RetweetExistsException;
import com.example.twitter.exceptions.RetweetNotFoundException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.RetweetRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RetweetServiceImpl implements RetweetService{

    private DtoMapping dtoMapping;
    private UserRepository userRepository;
    private RetweetRepository retweetRepository;

    @Autowired
    public RetweetServiceImpl(DtoMapping dtoMapping, UserRepository userRepository, RetweetRepository retweetRepository){
        this.dtoMapping = dtoMapping;
        this.userRepository = userRepository;
        this.retweetRepository = retweetRepository;
    }

    @Override
    public RetweetResponseDto save(RetweetRequestDto retweetRequestDto, String username) {
        if(retweetRepository.getRetweetOfTweetByTweetIdAndUsername(retweetRequestDto.getTweetId(), username) != null){
            throw new RetweetExistsException("Already retweeted.");
        }
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Retweet retweet = dtoMapping.MappingRetweetRequestToRetweet(retweetRequestDto);
        retweet.setUser(user);
        retweetRepository.save(retweet);
        return dtoMapping.MappingRetweetToRetweetResponseDto(retweet);
    }

    @Override
    public void delete(Long id) {
        if(retweetRepository.findById(id).isEmpty()){
            throw new RetweetNotFoundException("Retweet with id: " + id + " not found.");
        }
        retweetRepository.deleteById(id);
    }
}
