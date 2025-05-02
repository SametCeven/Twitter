package com.example.twitter.controller;

import com.example.twitter.entity.User;
import com.example.twitter.service.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public List<User> getAll(){
        return userService.getALl();
    }

    @GetMapping("/{id}")
    public User getById(
            @Positive @PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public User post(
            @Validated @RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User put(
            @Positive @PathVariable Long id,
            @Validated @RequestBody User user){
        return userService.put(id, user);
    }

    @PatchMapping("/{id}")
    public User patch(
            @Positive @PathVariable Long id,
            @Validated @RequestBody User user){
        return userService.patch(id,user);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Positive @PathVariable Long id){
        userService.delete(id);
    }




}
