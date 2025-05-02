package com.example.twitter.service;

import com.example.twitter.dto.UserRegister;
import com.example.twitter.entity.User;

public interface AuthService{

    User register(UserRegister userRegister);


}
