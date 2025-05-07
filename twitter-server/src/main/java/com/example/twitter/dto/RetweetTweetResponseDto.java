package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RetweetTweetResponseDto {
    private Long id;
    private Long userId;
    private Long tweetId;
}
