package com.example.twitter.service;

import com.example.twitter.dto.UserRegisterRequestDto;
import com.example.twitter.dto.UserRegisterResponseDto;
import com.example.twitter.entity.Role;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.EmailExistsException;
import com.example.twitter.exceptions.UsernameExistsException;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{
    private DtoMapping dtoMapping;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private final String ROLE_USER = "ROLE_USER";
    private final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    public AuthServiceImpl(DtoMapping dtoMapping, UserRepository userRepository, RoleRepository roleRepository){
        this.dtoMapping = dtoMapping;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto){
        Optional<User> userByUsername = userRepository.findUserByUsername(userRegisterRequestDto.getUsername());
        if(userByUsername.isPresent()) throw new UsernameExistsException("Username exits!");
        Optional<User> userByEmail = userRepository.findUserByEmail(userRegisterRequestDto.getEmail());
        if(userByEmail.isPresent()) throw new EmailExistsException("Email exists");

        Role userRole = roleRepository.findByAuthority(ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = dtoMapping.MappingUserRegisterRequestToUser(userRegisterRequestDto);
        user.setAuthorities(roles);

        userRepository.save(user);

        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
    }

    @Override
    public UserRegisterResponseDto putUser(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }

    @Override
    public UserRegisterResponseDto patchUser(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }

    @Override
    public UserRegisterResponseDto deleteUser(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }

    @Override
    public UserRegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto){
        Optional<User> userByUsername = userRepository.findUserByUsername(userRegisterRequestDto.getUsername());
        if(userByUsername.isPresent()) throw new UsernameExistsException("Username exists!");
        Optional<User> userByEmail = userRepository.findUserByEmail(userRegisterRequestDto.getEmail());
        if(userByEmail.isPresent()) throw new EmailExistsException("Email exists");

        Role userRole = roleRepository.findByAuthority(ROLE_ADMIN).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = dtoMapping.MappingUserRegisterRequestToUser(userRegisterRequestDto);
        user.setAuthorities(roles);

        userRepository.save(user);

        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
    }

    @Override
    public UserRegisterResponseDto putAdmin(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }

    @Override
    public UserRegisterResponseDto patchAdmin(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }

    @Override
    public UserRegisterResponseDto deleteAdmin(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }


}
