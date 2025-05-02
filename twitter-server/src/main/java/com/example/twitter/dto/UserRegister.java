package com.example.twitter.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegister {

    @NotNull
    @NotEmpty
    @Size(min = 5,max = 100)
    private String username;

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
