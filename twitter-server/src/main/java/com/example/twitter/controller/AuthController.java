package com.example.twitter.controller;

import com.example.twitter.dto.UserLoginRequestDto;
import com.example.twitter.dto.UserLoginResponseDto;
import com.example.twitter.dto.UserRegisterRequestDto;
import com.example.twitter.dto.UserRegisterResponseDto;
import com.example.twitter.security.JwtUtil;
import com.example.twitter.service.AuthService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
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

    @PostMapping("/login/user")
    public UserLoginResponseDto loginUser(@Validated @RequestBody UserLoginRequestDto userLoginRequestDto){
        return authService.loginUser(userLoginRequestDto);
    }

    @PostMapping("/login/admin")
    public UserLoginResponseDto loginAdmin(@Validated @RequestBody UserLoginRequestDto userLoginRequestDto){
        return authService.loginAdmin(userLoginRequestDto);
    }

    @PutMapping("/user/{id}")
    public UserRegisterResponseDto putUser(
            @Positive @PathVariable Long id,
            @Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto
        ){
        return authService.putUser(id, userRegisterRequestDto);
    }

    @PutMapping("/admin/{id}")
    public UserRegisterResponseDto putAdmin(
            @Positive @PathVariable Long id,
            @Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto
    ){
        return authService.putAdmin(id, userRegisterRequestDto);
    }

    @PatchMapping("/user/{id}")
    public UserRegisterResponseDto patchUser(
            @Positive @PathVariable Long id,
            @Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto
    ){
        return authService.patchUser(id, userRegisterRequestDto);
    }

    @PatchMapping("/admin/{id}")
    public UserRegisterResponseDto patchAdmin(
            @Positive @PathVariable Long id,
            @Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto
    ){
        return authService.patchAdmin(id, userRegisterRequestDto);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @Positive @PathVariable Long id){
        authService.deleteUser(id);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchAdmin(
            @Positive @PathVariable Long id){
        authService.deleteAdmin(id);
    }


}
