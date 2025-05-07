package com.example.twitter.utils;

import com.example.twitter.dto.*;
import com.example.twitter.entity.*;
import com.example.twitter.exceptions.CommentNotFoundException;
import com.example.twitter.exceptions.TweetNotFoundException;
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
                user.getRealUsername(),
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
    public UserLoginResponseDto MappingUserToUserLoginResponseDto(User user, String token) {
        return new UserLoginResponseDto(
                user.getId(),
                user.getRealUsername(),
                user.getEmail(),
                token
        );
    }

    @Override
    public UserLoginResponseDto MappingUserToUserLoginResponseDto(User user) {
        return new UserLoginResponseDto(
                user.getId(),
                user.getRealUsername(),
                user.getEmail(),
                null
        );
    }

    @Override
    public User MappingUserLoginRequestToUser(UserLoginRequestDto userLoginRequestDto) {
        String encodedPassword = passwordEncoder.encode(userLoginRequestDto.getPassword());
        User user = new User();
        user.setEmail(userLoginRequestDto.getEmail());
        user.setPassword(encodedPassword);
        return user;
    }

    @Override
    public UserResponseDto MappingUserToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getRealUsername(),
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
                comment.getUser().getId(),
                commentRepository.getLikeCount(comment.getId()),
                commentRepository.getRetweetCount(comment.getId())
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
    public LikeTweetResponseDto MappingLikeToLikeTweetResponseDto(Like like) {
        return new LikeTweetResponseDto(
                like.getId(),
                like.getUser().getId(),
                like.getTweet().getId()
        );
    }

    @Override
    public Like MappingLikeTweetRequestToLike(LikeTweetRequestDto likeTweetRequestDto) {
        Like like = new Like();
        Tweet tweet = tweetRepository
                .findById(likeTweetRequestDto.getTweetId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet with id: " + likeTweetRequestDto.getTweetId() + " not found."));
        like.setTweet(tweet);
        return like;
    }

    @Override
    public LikeCommentResponseDto MappingLikeToLikeCommentResponseResponseDto(Like like) {
        return new LikeCommentResponseDto(
                like.getId(),
                like.getUser().getId(),
                like.getComment().getId()
        );
    }

    @Override
    public Like MappingLikeCommentRequestToLike(LikeCommentRequestDto likeCommentRequestDto) {
        Like like = new Like();
        Comment comment = commentRepository
                .findById(likeCommentRequestDto.getCommentId())
                .orElseThrow(()-> new CommentNotFoundException("Comment with id: " + likeCommentRequestDto.getCommentId() + " not found."));
        like.setComment(comment);
        return like;
    }

    @Override
    public RetweetTweetResponseDto MappingRetweetToRetweetTweetResponseDto(Retweet retweet) {
        return new RetweetTweetResponseDto(
                retweet.getId(),
                retweet.getUser().getId(),
                retweet.getTweet().getId()
        );
    }

    @Override
    public Retweet MappingRetweetTweetRequestToRetweet(RetweetTweetRequestDto retweetTweetRequestDto) {
        Retweet retweet = new Retweet();
        Tweet tweet = tweetRepository
                .findById(retweetTweetRequestDto.getTweetId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet with id: " + retweetTweetRequestDto.getTweetId() + " not found."));
        retweet.setTweet(tweet);
        return retweet;
    }

    @Override
    public RetweetCommentResponseDto MappingRetweetToRetweetCommentResponseDto(Retweet retweet) {
        return new RetweetCommentResponseDto(
                retweet.getId(),
                retweet.getUser().getId(),
                retweet.getComment().getId()
        );
    }

    @Override
    public Retweet MappingRetweetCommentRequestToRetweet(RetweetCommentRequestDto retweetCommentRequestDto) {
        Retweet retweet = new Retweet();
        Comment comment = commentRepository
                .findById(retweetCommentRequestDto.getCommentId())
                .orElseThrow(()-> new TweetNotFoundException("Tweet with id: " + retweetCommentRequestDto.getCommentId() + " not found."));
        retweet.setComment(comment);
        return retweet;
    }


}
