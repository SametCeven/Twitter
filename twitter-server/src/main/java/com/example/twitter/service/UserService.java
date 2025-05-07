package com.example.twitter.service;

import com.example.twitter.dto.UserResponseDto;
import com.example.twitter.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getALl();
    UserResponseDto getById(Long id);

}
