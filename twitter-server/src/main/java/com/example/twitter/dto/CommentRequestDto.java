package com.example.twitter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequestDto {
    private String commentText;
    private byte[] picture;
    private Long tweetId;
}
