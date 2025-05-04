package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDto {
    private Long id;
    private String commentText;
    private byte[] picture;
    private TweetResponseDto tweetResponseDto;
    private UserResponseDto userResponseDto;
}
