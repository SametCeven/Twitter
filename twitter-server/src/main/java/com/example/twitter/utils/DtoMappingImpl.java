package com.example.twitter.utils;

import com.example.twitter.dto.TweetRequestDto;
import com.example.twitter.dto.TweetResponseDto;
import com.example.twitter.dto.UserResponseDto;
import com.example.twitter.entity.Tweet;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMappingImpl implements DtoMapping{

    private UserRepository userRepository;

    @Autowired
    public DtoMappingImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public TweetResponseDto MappingTweetToTweetResponseDto(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getId(),
                tweet.getTweetText(),
                tweet.getCreatedDate(),
                tweet.getPicture(),
                new UserResponseDto(
                        tweet.getUser().getId(),
                        tweet.getUser().getUsername(),
                        tweet.getUser().getEmail(),
                        tweet.getUser().getFirstName(),
                        tweet.getUser().getLastName(),
                        tweet.getUser().getProfilePicture(),
                        userRepository.findUsersAuthorities(tweet.getUser().getId())
                )
        );
    }

    @Override
    public Tweet MappingTweetRequestToTweet(TweetRequestDto tweetRequestDto) {
        Tweet tweet = new Tweet();
        tweet.setTweetText(tweetRequestDto.getTweetText());
        tweet.setCreatedDate(tweetRequestDto.getCreatedDate());
        tweet.setPicture(tweetRequestDto.getPicture());
        return tweet;
    }


}
