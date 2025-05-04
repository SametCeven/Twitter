package com.example.twitter.utils;

import com.example.twitter.dto.*;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;

public interface DtoMapping {
    UserRegisterResponseDto MappingUserToUserRegisterResponseDto(User user);
    User MappingUserRegisterRequestToUser(UserRegisterRequestDto userRegisterRequestDto);
    UserResponseDto MappingUserToUserResponseDto(User user);
    TweetResponseDto MappingTweetToTweetResponseDto(Tweet tweet);
    Tweet MappingTweetRequestToTweet(TweetRequestDto tweetRequestDto);
    CommentResponseDto MappingCommentToCommentResponseDto(Comment comment);
    Comment MappingCommentRequestToComment(CommentRequestDto commentRequestDto);


}
