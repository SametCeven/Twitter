package com.example.twitter.service;

import com.example.twitter.dto.UserRegisterRequestDto;
import com.example.twitter.dto.UserRegisterResponseDto;

public interface AuthService{

    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto putUser(Long id, UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto patchUser(Long id, UserRegisterRequestDto userRegisterRequestDto);
    void deleteUser(Long id);
    UserRegisterResponseDto loginUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto putAdmin(Long id, UserRegisterRequestDto userRegisterRequestDto);
    UserRegisterResponseDto patchAdmin(Long id, UserRegisterRequestDto userRegisterRequestDto);
    void deleteAdmin(Long id);
    UserRegisterResponseDto loginAdmin(UserRegisterRequestDto userRegisterRequestDto);

}
