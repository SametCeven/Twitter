package com.example.twitter.service;

import com.example.twitter.entity.User;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getALl() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->new UsernameNotFoundException("User with " + id + " not found."));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User put(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }

    @Override
    public User patch(Long id, User user) {
        User userOptional = userRepository
                .findById(id)
                .orElseThrow(()->new UserNotFoundException("User with " + id + " not found."));
        if(user.getUsername() != null) userOptional.setUsername(user.getUsername());
        if(user.getEmail() != null) userOptional.setEmail(user.getEmail());
        if(user.getPassword() != null) userOptional.setPassword(user.getPassword());
        if(user.getFirstName() != null) userOptional.setFirstName(user.getFirstName());
        if(user.getLastName() != null) userOptional.setLastName(user.getLastName());
        if(user.getProfilePicture() != null) userOptional.setProfilePicture(user.getProfilePicture());
        return userRepository.save(userOptional);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
