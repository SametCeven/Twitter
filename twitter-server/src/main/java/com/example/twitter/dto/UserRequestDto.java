package com.example.twitter.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotEmpty
    @NotNull
    private String email;

}
