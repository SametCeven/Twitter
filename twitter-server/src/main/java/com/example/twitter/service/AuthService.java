package com.example.twitter.service;

import com.example.twitter.dto.UserRegisterRequestDto;
import com.example.twitter.dto.UserRegisterResponseDto;

public interface AuthService{

    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto putUser(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto patchUser(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto deleteUser(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto loginUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto putAdmin(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto patchAdmin(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto deleteAdmin(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto loginAdmin(UserRegisterRequestDto userRegisterRequestDto);

}
