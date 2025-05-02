package com.example.twitter.controller;

import com.example.twitter.entity.*;
import com.example.twitter.service.LikeService;
import com.example.twitter.service.RetweetService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private RetweetService retweetService;

    @Autowired
    public RetweetController(RetweetService retweetService){
        this.retweetService = retweetService;
    }

    @GetMapping
    public List<Retweet> getAll(){
        return retweetService.getALl();
    }

    @GetMapping("/{id}")
    public Retweet getById(
            @Positive @PathVariable Long id){
        return retweetService.getById(id);
    }

    @PostMapping
    public Retweet post(
            @Validated @RequestBody Retweet retweet,
            @Validated @RequestBody Comment comment,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return retweetService.save(retweet, comment, tweet, user);
    }

    @PutMapping("/{id}")
    public Retweet put(
            @Positive @PathVariable Long id,
            @Positive @PathVariable Retweet retweet,
            @Validated @RequestBody Comment comment,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return retweetService.put(id, retweet, comment, tweet, user);
    }

    @PatchMapping("/{id}")
    public Retweet patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody Retweet retweet){
        return retweetService.patch(id,retweet);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Positive @PathVariable Long id){
        retweetService.delete(id);
    }
}
