package com.example.twitter.service;

import com.example.twitter.dto.UserResponseDto;
import com.example.twitter.entity.User;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> getALl() {
        List<UserResponseDto> responseDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User user:users){
            responseDtos.add(new UserResponseDto(
                    user.getId(),
                    user.getRealUsername(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getProfilePicture(),
                    userRepository.findUsersAuthorities(user.getId())));
        }
        return responseDtos;
    }

    @Override
    public UserResponseDto getById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new UsernameNotFoundException("User with " + id + " not found."));
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getProfilePicture(),
                userRepository.findUsersAuthorities(id));
    }




}
