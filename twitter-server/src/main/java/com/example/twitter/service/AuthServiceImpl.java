package com.example.twitter.service;

import com.example.twitter.dto.UserRegister;
import com.example.twitter.entity.Role;
import com.example.twitter.entity.User;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private final String USER_ROLE = "USER";
    private final String ADMIN_ROLE = "ADMIN";

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserRegister userRegister){
        String encodedPassword = passwordEncoder.encode(userRegister.getPassword());
        Role userRole = roleRepository.findByAuthority(USER_ROLE).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);

        return userRepository.save(user);
    }



}
