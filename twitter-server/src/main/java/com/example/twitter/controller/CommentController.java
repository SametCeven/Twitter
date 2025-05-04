package com.example.twitter.controller;

import com.example.twitter.dto.CommentRequestDto;
import com.example.twitter.dto.CommentResponseDto;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.CommentService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto post(
            @Validated @RequestBody CommentRequestDto commentRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return commentService.save(commentRequestDto, username);
    }

    @GetMapping
    public List<Comment> getAll(){
        return commentService.getALl();
    }

    @GetMapping("/{id}")
    public Comment getById(
            @Positive @PathVariable Long id){
        return commentService.getById(id);
    }



    @PutMapping("/{id}")
    public Comment put(
            @Positive @PathVariable Long id,
            @Validated @RequestBody Comment comment,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return commentService.put(id, comment, tweet, user);
    }

    @PatchMapping("/{id}")
    public Comment patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody Comment comment){
        return commentService.patch(id,comment);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Positive @PathVariable Long id){
        commentService.delete(id);
    }

}
