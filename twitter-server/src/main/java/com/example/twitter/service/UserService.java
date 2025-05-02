package com.example.twitter.service;

import com.example.twitter.entity.User;

import java.util.List;

public interface UserService {
    List<User> getALl();
    User getById(Long id);
    User save(User user);
    User put(Long id, User user);
    User patch(Long id, User user);
    void delete(Long id);

}
