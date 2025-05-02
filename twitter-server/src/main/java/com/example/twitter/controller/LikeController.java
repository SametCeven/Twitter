package com.example.twitter.controller;

import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Like;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.CommentService;
import com.example.twitter.service.LikeService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAll(){
        return likeService.getALl();
    }

    @GetMapping("/{id}")
    public Like getById(
            @Positive @PathVariable Long id){
        return likeService.getById(id);
    }

    @PostMapping
    public Like post(
            @Validated @RequestBody Like like,
            @Validated @RequestBody Comment comment,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return likeService.save(like, comment, tweet, user);
    }

    @PutMapping("/{id}")
    public Like put(
            @Positive @PathVariable Long id,
            @Positive @PathVariable Like like,
            @Validated @RequestBody Comment comment,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return likeService.put(id, like, comment, tweet, user);
    }

    @PatchMapping("/{id}")
    public Like patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody Like like){
        return likeService.patch(id,like);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Positive @PathVariable Long id){
        likeService.delete(id);
    }
}
