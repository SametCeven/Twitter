package com.example.twitter.service;

import com.example.twitter.dto.UserRegisterRequestDto;
import com.example.twitter.dto.UserRegisterResponseDto;
import com.example.twitter.entity.Role;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.EmailExistsException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.exceptions.UsernameExistsException;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    private final String ROLE_USER = "ROLE_USER";
    private final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    public AuthServiceImpl(
            DtoMapping dtoMapping,
            UserRepository userRepository,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder){
        this.dtoMapping = dtoMapping;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
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
    public UserRegisterResponseDto putUser(Long id, UserRegisterRequestDto userRegisterRequestDto) {
        Optional<User> optionalUser = userRepository.findUserByUsername(userRegisterRequestDto.getUsername());
        User user = dtoMapping.MappingUserRegisterRequestToUser(userRegisterRequestDto);
        Role userRole = roleRepository.findByAuthority(ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setAuthorities(roles);
        if(optionalUser.isPresent()){
            user.setId(id);
            userRepository.save(user);
            return dtoMapping.MappingUserToUserRegisterResponseDto(user);
        }
        userRepository.save(user);
        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
    }

    @Override
    public UserRegisterResponseDto patchUser(Long id, UserRegisterRequestDto userRegisterRequestDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id: " + id + " not found."));

        if(userRegisterRequestDto.getUsername() != null) user.setUsername(userRegisterRequestDto.getUsername());
        if(userRegisterRequestDto.getEmail() != null) user.setEmail(userRegisterRequestDto.getEmail());
        if(userRegisterRequestDto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id: " + id + " not found."));
        userRepository.deleteById(id);
    }

    @Override
    public UserRegisterResponseDto loginUser(UserRegisterRequestDto userRegisterRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userRegisterRequestDto.getUsername(),
                userRegisterRequestDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository
                .findUserByUsername(userRegisterRequestDto.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User with username: " + userRegisterRequestDto.getUsername() + " not found."));
        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
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
    public UserRegisterResponseDto putAdmin(Long id, UserRegisterRequestDto userRegisterRequestDto) {
        Optional<User> optionalUser = userRepository.findUserByUsername(userRegisterRequestDto.getUsername());
        User user = dtoMapping.MappingUserRegisterRequestToUser(userRegisterRequestDto);
        Role userRole = roleRepository.findByAuthority(ROLE_ADMIN).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setAuthorities(roles);
        if(optionalUser.isPresent()){
            user.setId(id);
            userRepository.save(user);
            return dtoMapping.MappingUserToUserRegisterResponseDto(user);
        }
        userRepository.save(user);
        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
    }

    @Override
    public UserRegisterResponseDto patchAdmin(Long id, UserRegisterRequestDto userRegisterRequestDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id: " + id + " not found."));

        if(userRegisterRequestDto.getUsername() != null) user.setUsername(userRegisterRequestDto.getUsername());
        if(userRegisterRequestDto.getEmail() != null) user.setEmail(userRegisterRequestDto.getEmail());
        if(userRegisterRequestDto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(userRegisterRequestDto.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
        return dtoMapping.MappingUserToUserRegisterResponseDto(user);
    }

    @Override
    public void deleteAdmin(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id: " + id + " not found."));
        userRepository.deleteById(id);
    }

    @Override
    public UserRegisterResponseDto loginAdmin(UserRegisterRequestDto userRegisterRequestDto) {
        return loginUser(userRegisterRequestDto);
    }


}
