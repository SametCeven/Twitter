package com.example.twitter.service;

import com.example.twitter.dto.CommentRequestDto;
import com.example.twitter.dto.CommentResponseDto;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.CommentNotFoundException;
import com.example.twitter.exceptions.TweetNotFoundException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.CommentRepository;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private DtoMapping dtoMapping;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private CommentRepository commentRepository;


    @Autowired
    public CommentServiceImpl(DtoMapping dtoMapping, UserRepository userRepository,TweetRepository tweetRepository, CommentRepository commentRepository){
        this.dtoMapping = dtoMapping;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with " + username + " username not found."));
        Comment comment = dtoMapping.MappingCommentRequestToComment(commentRequestDto);

        user.addComment(comment);
        comment.getTweet().addComment(comment);
        comment.setUser(user);
        commentRepository.save(comment);
        return dtoMapping.MappingCommentToCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto put(Long id, CommentRequestDto commentRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with " + username + " username not found."));
        Comment comment = dtoMapping.MappingCommentRequestToComment(commentRequestDto);
        
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            comment.setId(id);
            comment.setUser(user);
            commentRepository.save(comment);
            return dtoMapping.MappingCommentToCommentResponseDto(comment);
        }
        user.addComment(comment);
        comment.getTweet().addComment(comment);
        commentRepository.save(comment);
        return dtoMapping.MappingCommentToCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto patch(Long id, CommentRequestDto commentRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with " + username + " username not found."));
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(()-> new CommentNotFoundException("Commment with id: " + id + " not found."));

        if(commentRequestDto.getCommentText() != null) comment.setCommentText(commentRequestDto.getCommentText());
        if(commentRequestDto.getPicture() != null) comment.setPicture(commentRequestDto.getPicture());
        if(commentRequestDto.getTweetId() != null) {
            Tweet tweet = tweetRepository
                    .findById(commentRequestDto.getTweetId())
                    .orElseThrow(() -> new TweetNotFoundException("Tweet with id: " + id + " not found."));
            comment.setTweet(tweet);
        }
        commentRepository.save(comment);
        return dtoMapping.MappingCommentToCommentResponseDto(comment);
    }


    @Override
    public void delete(Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(()-> new CommentNotFoundException("Comment with id: " + id + " not found."));
        commentRepository.deleteById(id);
    }

    
}
