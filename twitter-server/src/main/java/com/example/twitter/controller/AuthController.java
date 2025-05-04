package com.example.twitter.controller;

import com.example.twitter.dto.UserRegisterRequestDto;
import com.example.twitter.dto.UserRegisterResponseDto;
import com.example.twitter.entity.User;
import com.example.twitter.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto registerUser(@Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return authService.registerUser(userRegisterRequestDto);
    }

    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto registerAdmin(@Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return authService.registerAdmin(userRegisterRequestDto);
    }

    @PutMapping("/register/user")
    public UserRegisterResponseDto putUser(@Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto){

    }

}
