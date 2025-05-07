package com.example.twitter.utils;

import com.example.twitter.dto.*;
import com.example.twitter.entity.*;

public interface DtoMapping {
    UserRegisterResponseDto MappingUserToUserRegisterResponseDto(User user);
    User MappingUserRegisterRequestToUser(UserRegisterRequestDto userRegisterRequestDto);

    UserLoginResponseDto MappingUserToUserLoginResponseDto(User user, String token);
    User MappingUserLoginRequestToUser(UserLoginRequestDto userLoginRequestDto);
    UserLoginResponseDto MappingUserToUserLoginResponseDto(User user);

    UserResponseDto MappingUserToUserResponseDto(User user);

    TweetResponseDto MappingTweetToTweetResponseDto(Tweet tweet);
    Tweet MappingTweetRequestToTweet(TweetRequestDto tweetRequestDto);

    CommentResponseDto MappingCommentToCommentResponseDto(Comment comment);
    Comment MappingCommentRequestToComment(CommentRequestDto commentRequestDto);

    LikeTweetResponseDto MappingLikeToLikeTweetResponseDto(Like like);
    Like MappingLikeTweetRequestToLike(LikeTweetRequestDto likeTweetRequestDto);

    LikeCommentResponseDto MappingLikeToLikeCommentResponseResponseDto(Like like);
    Like MappingLikeCommentRequestToLike(LikeCommentRequestDto likeCommentRequestDto);

    RetweetTweetResponseDto MappingRetweetToRetweetTweetResponseDto(Retweet retweet);
    Retweet MappingRetweetTweetRequestToRetweet(RetweetTweetRequestDto retweetTweetRequestDto);

    RetweetCommentResponseDto MappingRetweetToRetweetCommentResponseDto(Retweet retweet);
    Retweet MappingRetweetCommentRequestToRetweet(RetweetCommentRequestDto retweetCommentRequestDto);

}
