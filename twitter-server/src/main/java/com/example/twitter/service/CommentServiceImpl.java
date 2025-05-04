package com.example.twitter.service;

import com.example.twitter.dto.CommentRequestDto;
import com.example.twitter.dto.CommentResponseDto;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.CommentNotFoundException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.CommentRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private DtoMapping dtoMapping;
    private UserRepository userRepository;
    private CommentRepository commentRepository;


    @Autowired
    public CommentServiceImpl(DtoMapping dtoMapping, UserRepository userRepository, CommentRepository commentRepository){
        this.dtoMapping = dtoMapping;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto, String username) {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with " + username + " username not found."));
        Comment comment = dtoMapping.MappingCommentRequestToComment(commentRequestDto);

        user.addComment(comment);
        comment.getTweet().addComment(comment);
        comment.setUser(user);
        commentRepository.save(comment);
        return dtoMapping.MappingCommentToCommentResponseDto(comment);
    }

    @Override
    public List<Comment> getALl() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository
                .findById(id)
                .orElseThrow(()->new CommentNotFoundException("Comment with " + id + " not found."));
    }



    @Override
    public Comment put(Long id, Comment comment, Tweet tweet, User user) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            comment.setId(id);
            return commentRepository.save(comment);
        }
        user.addComment(comment);
        tweet.addComment(comment);
        return commentRepository.save(comment);
    }

    @Override
    public Comment patch(Long id, Comment comment) {
        return comment;
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
