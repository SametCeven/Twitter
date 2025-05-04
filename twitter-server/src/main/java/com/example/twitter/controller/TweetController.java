package com.example.twitter.controller;

import com.example.twitter.dto.TweetRequestDto;
import com.example.twitter.dto.TweetResponseDto;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.TweetService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto post(
            @Validated @RequestBody TweetRequestDto tweetRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return tweetService.save(tweetRequestDto,username);
    }

    @GetMapping("/findByUserId")
    public List<TweetResponseDto> getByUserId(
            Authentication authentication){
        String username = authentication.getName();
        return tweetService.getByUserId(username);
    }

    @GetMapping("/findById/{id}")
    public TweetResponseDto getById(
            @Positive @PathVariable Long id){
        return tweetService.getById(id);
    }

    @PutMapping("/{id}")
    public TweetResponseDto put(
            @Positive @PathVariable Long id,
            @Validated @RequestBody TweetRequestDto tweetRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return tweetService.put(id, tweetRequestDto, username);
    }

    @PatchMapping("/{id}")
    public TweetResponseDto patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody TweetRequestDto tweetRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return tweetService.patch(id, tweetRequestDto, username);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Positive @PathVariable Long id){
        tweetService.delete(id);
    }

}
