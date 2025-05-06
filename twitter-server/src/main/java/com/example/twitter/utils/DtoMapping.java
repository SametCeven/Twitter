package com.example.twitter.utils;

import com.example.twitter.dto.*;
import com.example.twitter.entity.*;

public interface DtoMapping {
    UserRegisterResponseDto MappingUserToUserRegisterResponseDto(User user);
    User MappingUserRegisterRequestToUser(UserRegisterRequestDto userRegisterRequestDto);

    UserLoginResponseDto MappingUserToUserLoginResponseDto(User user);
    User MappingUserLoginRequestToUser(UserLoginRequestDto userLoginRequestDto);

    UserResponseDto MappingUserToUserResponseDto(User user);
    TweetResponseDto MappingTweetToTweetResponseDto(Tweet tweet);

    Tweet MappingTweetRequestToTweet(TweetRequestDto tweetRequestDto);
    CommentResponseDto MappingCommentToCommentResponseDto(Comment comment);
    Comment MappingCommentRequestToComment(CommentRequestDto commentRequestDto);

    LikeResponseDto MappingLikeToLikeResponseDto(Like like);
    Like MappingLikeRequestToLike(LikeRequestDto likeRequestDto);

    RetweetResponseDto MappingRetweetToRetweetResponseDto(Retweet retweet);
    Retweet MappingRetweetRequestToRetweet(RetweetRequestDto retweetRequestDto);


}
