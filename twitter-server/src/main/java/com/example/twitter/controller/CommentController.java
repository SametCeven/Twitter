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

    @PutMapping("/{id}")
    public CommentResponseDto put(
            @Positive @PathVariable Long id,
            @Validated @RequestBody CommentRequestDto commentRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return commentService.put(id, commentRequestDto,username);
    }

    @PatchMapping("/{id}")
    public CommentResponseDto patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody CommentRequestDto commentRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return commentService.patch(id, commentRequestDto, username);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Positive @PathVariable Long id){
        commentService.delete(id);
    }

}
