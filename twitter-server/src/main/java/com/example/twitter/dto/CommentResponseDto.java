package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDto {
    private Long id;
    private String commentText;
    private LocalDateTime createdDate;
    private byte[] picture;
    private Long tweetId;
    private Long userId;
    private Integer likeCount;
    private Integer retweetCount;
}
