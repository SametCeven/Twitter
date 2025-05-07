package com.example.twitter.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginRequestDto {

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 150)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 50)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$")
    private String password;

}
