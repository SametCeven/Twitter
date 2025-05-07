package com.example.twitter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterErrorResponse {
    private String message;
    private Integer status;
    private LocalDateTime localDateTime;
}
