package com.example.twitter.utils;

import com.example.twitter.dto.*;
import com.example.twitter.entity.*;
import com.example.twitter.exceptions.CommentNotFoundException;
import com.example.twitter.exceptions.TweetNotFoundException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.CommentRepository;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoMappingImpl implements DtoMapping{

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private CommentRepository commentRepository;

    @Autowired
    public DtoMappingImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, TweetRepository tweetRepository, CommentRepository commentRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
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
        List<Comment> comments = tweetRepository.getCommentByTweetId(tweet.getId());
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for(Comment comment:comments){
            commentResponseDto.add(MappingCommentToCommentResponseDto(comment));
        }
        Integer likeCount = tweetRepository.getLikeCount(tweet.getId());
        Integer retweetCount = tweetRepository.getRetweetCount(tweet.getId());

        return new TweetResponseDto(
                tweet.getId(),
                tweet.getTweetText(),
                tweet.getCreatedDate(),
                tweet.getPicture(),
                tweet.getUser().getId(),
                commentResponseDto,
                likeCount,
                retweetCount
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
        return new CommentResponseDto(
                comment.getId(),
                comment.getCommentText(),
                comment.getCreatedDate(),
                comment.getPicture(),
                comment.getTweet().getId(),
                comment.getUser().getId()
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

    @Override
    public LikeResponseDto MappingLikeToLikeResponseDto(Like like) {
        return new LikeResponseDto(
                like.getId(),
                like.getUser().getId(),
                like.getTweet().getId()
        );
    }

    @Override
    public Like MappingLikeRequestToLike(LikeRequestDto likeRequestDto) {
        Like like = new Like();
        Tweet tweet = tweetRepository
                .findById(likeRequestDto.getTweetId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet with id: " + likeRequestDto.getTweetId() + " not found."));
        like.setTweet(tweet);
        return  like;
    }

    @Override
    public RetweetResponseDto MappingRetweetToRetweetResponseDto(Retweet retweet) {
        return new RetweetResponseDto(
                retweet.getId(),
                retweet.getUser().getId(),
                retweet.getTweet().getId()
        );
    }

    @Override
    public Retweet MappingRetweetRequestToRetweet(RetweetRequestDto retweetRequestDto) {
        Retweet retweet = new Retweet();
        Tweet tweet = tweetRepository
                .findById(retweetRequestDto.getTweetId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet with id: " + retweetRequestDto.getTweetId() + " not found."));
        retweet.setTweet(tweet);
        return retweet;
    }


}
