package com.example.twitter.service;

import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.CommentNotFoundException;
import com.example.twitter.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
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
    public Comment save(Comment comment, Tweet tweet, User user) {
        user.addComment(comment);
        tweet.addComment(comment);
        return commentRepository.save(comment);
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
