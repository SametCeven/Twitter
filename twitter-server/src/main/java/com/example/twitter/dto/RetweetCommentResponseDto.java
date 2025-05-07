package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RetweetCommentResponseDto {
    private Long id;
    private Long userId;
    private Long commentId;
}
