package com.example.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterResponseDto {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private byte[] profilePicture;

}
