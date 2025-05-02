package com.example.twitter.controller;

import com.example.twitter.dto.UserRegister;
import com.example.twitter.entity.User;
import com.example.twitter.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping
    public User register(@Validated @RequestBody UserRegister userRegister){
        return authService.register(userRegister);
    }

}
