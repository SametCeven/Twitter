package com.example.twitter.utils;

import com.example.twitter.dto.TweetRequestDto;
import com.example.twitter.dto.TweetResponseDto;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

public interface DtoMapping {

    TweetResponseDto MappingTweetToTweetResponseDto(Tweet tweet);
    Tweet MappingTweetRequestToTweet(TweetRequestDto tweetRequestDto);


}
