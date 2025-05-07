package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;
    private String tweetText;
    private LocalDateTime createdDate;
    private byte[] picture;
    private Long userId;
    private List<CommentResponseDto> commentResponseDtos;
    private Integer likeCount;
    private Integer retweetCount;
}
