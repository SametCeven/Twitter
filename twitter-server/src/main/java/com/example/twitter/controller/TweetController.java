package com.example.twitter.controller;

import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.TweetService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService){
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<Tweet> getAll(){
        return tweetService.getALl();
    }

    @GetMapping("/{id}")
    public Tweet getById(
            @Positive @PathVariable Long id){
        return tweetService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tweet post(
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return tweetService.save(tweet,user);
    }

    @PutMapping("/{id}")
    public Tweet put(
            @Positive @PathVariable Long id,
            @Validated @RequestBody Tweet tweet,
            @Validated @RequestBody User user){
        return tweetService.put(id, tweet, user);
    }

    @PatchMapping("/{id}")
    public Tweet patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody Tweet tweet){
        return tweetService.patch(id, tweet);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Positive @PathVariable Long id){
        tweetService.delete(id);
    }

}
