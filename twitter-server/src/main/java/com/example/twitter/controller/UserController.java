package com.example.twitter.controller;

import com.example.twitter.dto.UserResponseDto;
import com.example.twitter.service.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAll(){
        return userService.getALl();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(
            @Positive @PathVariable Long id){
        return userService.getById(id);
    }





}
