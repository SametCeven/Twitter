package com.example.twitter.service;

import com.example.twitter.dto.RetweetCommentRequestDto;
import com.example.twitter.dto.RetweetCommentResponseDto;
import com.example.twitter.dto.RetweetTweetRequestDto;
import com.example.twitter.dto.RetweetTweetResponseDto;
import com.example.twitter.entity.Retweet;
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
    public RetweetTweetResponseDto saveTweet(RetweetTweetRequestDto retweetTweetRequestDto, String username) {
        if(retweetRepository.getRetweetOfTweetByTweetIdAndUsername(retweetTweetRequestDto.getTweetId(), username) != null){
            throw new RetweetExistsException("Already retweeted.");
        }
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Retweet retweet = dtoMapping.MappingRetweetTweetRequestToRetweet(retweetTweetRequestDto);
        retweet.setUser(user);
        retweetRepository.save(retweet);
        return dtoMapping.MappingRetweetToRetweetTweetResponseDto(retweet);
    }

    @Override
    public void deleteTweet(Long tweetId, String username) {
        Retweet retweet = retweetRepository.getRetweetOfTweetByTweetIdAndUsername(tweetId, username);
        if(retweet != null){
            retweetRepository.delete(retweet);
        }else{
            throw new RetweetNotFoundException("Retweet does not exit.");
        }
    }

    @Override
    public Boolean isRetweeted(Long tweetId, String username) {
        if(retweetRepository.getRetweetOfTweetByTweetIdAndUsername(tweetId, username) != null){
            return true;
        }
        return false;
    }

    @Override
    public RetweetCommentResponseDto saveComment(RetweetCommentRequestDto retweetCommentRequestDto, String username) {
        if(retweetRepository.getRetweetOfCommentByCommentIdAndUsername(retweetCommentRequestDto.getCommentId(), username) != null){
            throw new RetweetExistsException("Already retweeted.");
        }
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Retweet retweet = dtoMapping.MappingRetweetCommentRequestToRetweet(retweetCommentRequestDto);
        retweet.setUser(user);
        retweetRepository.save(retweet);
        return dtoMapping.MappingRetweetToRetweetCommentResponseDto(retweet);
    }

    @Override
    public void deleteComment(Long id) {
        if(retweetRepository.findById(id).isEmpty()){
            throw new RetweetNotFoundException("Retweet with id: " + id + " not found.");
        }
        retweetRepository.deleteById(id);
    }
}
