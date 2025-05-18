package com.example.twitter.controller;

import com.example.twitter.dto.RetweetCommentRequestDto;
import com.example.twitter.dto.RetweetCommentResponseDto;
import com.example.twitter.dto.RetweetTweetRequestDto;
import com.example.twitter.dto.RetweetTweetResponseDto;
import com.example.twitter.service.RetweetService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private RetweetService retweetService;

    @Autowired
    public RetweetController(RetweetService retweetService){
        this.retweetService = retweetService;
    }

    @PostMapping("/tweet")
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetTweetResponseDto postTweet(
            @RequestBody RetweetTweetRequestDto retweetTweetRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return retweetService.saveTweet(retweetTweetRequestDto, username);
    }

    @DeleteMapping("/tweet/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(
            @Positive @PathVariable Long tweetId,
            Authentication authentication){
        String username = authentication.getName();
        retweetService.deleteTweet(tweetId, username);
    }

    @GetMapping("/tweet/isRetweeted")
    public Boolean tweetIsRetweeted(
            @RequestParam Long tweetId,
            Authentication authentication){
        String username = authentication.getName();
        return retweetService.isRetweeted(tweetId, username);
    }

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetCommentResponseDto postComment(
            @RequestBody RetweetCommentRequestDto retweetCommentRequestDto,
            Authentication authentication){
        String username = authentication.getName();
        return retweetService.saveComment(retweetCommentRequestDto, username);
    }

    @DeleteMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @Positive @PathVariable Long id){
        retweetService.deleteComment(id);
    }
}
