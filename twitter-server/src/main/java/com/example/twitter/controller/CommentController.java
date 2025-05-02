package com.example.twitter.controller;

import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.CommentService;
import com.example.twitter.service.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Comment> getAll(){
        return commentService.getALl();
    }

    @GetMapping("/{id}")
    public Comment getById(
            @Positive @PathVariable Long id){
        return commentService.getById(id);
    }

    @PostMapping
    public Comment post(
            @Validated @RequestBody Comment comment,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return commentService.save(comment, tweet, user);
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
