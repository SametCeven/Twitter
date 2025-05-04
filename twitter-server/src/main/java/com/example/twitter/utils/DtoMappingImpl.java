package com.example.twitter.utils;

import com.example.twitter.dto.*;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.TweetNotFoundException;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DtoMappingImpl implements DtoMapping{

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    @Autowired
    public DtoMappingImpl(UserRepository userRepository, TweetRepository tweetRepository){
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public UserRegisterResponseDto MappingUserToUserRegisterResponseDto(User user) {
        return new UserRegisterResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    @Override
    public User MappingUserRegisterRequestToUser(UserRegisterRequestDto userRegisterRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
        User user = new User();
        user.setUsername(userRegisterRequestDto.getUsername());
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPassword(encodedPassword);
        return user;
    }

    @Override
    public UserResponseDto MappingUserToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getProfilePicture(),
                userRepository.findUsersAuthorities(user.getId()));
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

    @Override
    public CommentResponseDto MappingCommentToCommentResponseDto(Comment comment) {
        TweetResponseDto tweetResponseDto = MappingTweetToTweetResponseDto(comment.getTweet());
        UserResponseDto userResponseDto = MappingUserToUserResponseDto(comment.getUser());
        return new CommentResponseDto(
                comment.getId(),
                comment.getCommentText(),
                comment.getPicture(),
                tweetResponseDto,
                userResponseDto
        );
    }

    @Override
    public Comment MappingCommentRequestToComment(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        comment.setCommentText(commentRequestDto.getCommentText());
        comment.setPicture(commentRequestDto.getPicture());
        Tweet tweet = tweetRepository
                .findById(commentRequestDto.getTweetId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet with id: " + commentRequestDto.getTweetId() + " not found."));
        comment.setTweet(tweet);
        return comment;
    }


}
