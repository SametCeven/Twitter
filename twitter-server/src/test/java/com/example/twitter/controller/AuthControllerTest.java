package com.example.twitter.controller;

import com.example.twitter.config.SecurityConfig;
import com.example.twitter.dto.*;
import com.example.twitter.entity.*;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.security.JwtUtil;
import com.example.twitter.service.AuthService;
import com.example.twitter.utils.DtoMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    UserDetailsService userDetailsService;

    @MockBean
    SecurityConfig securityConfig;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    DtoMapping dtoMapping;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    Authentication authentication;

    Role role;
    Set<Role> roleSet = new HashSet<>();
    User user1;
    User user2;
    Tweet tweet;
    Comment comment;
    Like like1;
    Like like2;
    Retweet retweet1;
    Retweet retweet2;
    UserResponseDto user1ResponseDto;
    UserResponseDto user2ResponseDto;
    UserRegisterRequestDto user1RegisterRequestDto;
    UserRegisterResponseDto user1RegisterResponseDto;
    UserLoginRequestDto user1LoginRequestDto;
    UserLoginResponseDto user1LoginResponseDto;


    @BeforeEach
    void setUp(){

        role = new Role();
        role.setId(1L);
        role.setAuthority("ROLE_USER");
        roleSet.add(role);

        user1 = new User();
        user1.setId(1L);
        user1.setUsername("testUsername");
        user1.setEmail("test@gmail.com");
        user1.setPassword("test1Password#");
        user1.setFirstName("testFirstName");
        user1.setLastName("testLastName");
        user1.setProfilePicture(new byte[123]);
        user1.setAuthorities(roleSet);

        user1ResponseDto = new UserResponseDto();
        user1ResponseDto.setId(user1.getId());
        user1ResponseDto.setUsername(user1.getUsername());
        user1ResponseDto.setEmail(user1.getEmail());
        user1ResponseDto.setFirstName(user1.getFirstName());
        user1ResponseDto.setLastName(user1.getLastName());
        user1ResponseDto.setProfilePicture(user1.getProfilePicture());
        user1ResponseDto.setAuthorities(user1
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toSet())
        );

        user1RegisterRequestDto = new UserRegisterRequestDto();
        user1RegisterRequestDto.setUsername(user1.getRealUsername());
        user1RegisterRequestDto.setEmail(user1.getEmail());
        user1RegisterRequestDto.setPassword(user1.getPassword());

        user1RegisterResponseDto = new UserRegisterResponseDto();
        user1RegisterResponseDto.setId(user1.getId());
        user1RegisterResponseDto.setUsername(user1.getRealUsername());
        user1RegisterResponseDto.setEmail(user1.getEmail());

        user1LoginRequestDto = new UserLoginRequestDto();
        user1LoginRequestDto.setEmail(user1.getEmail());
        user1LoginRequestDto.setPassword(user1.getPassword());

        user1LoginResponseDto = new UserLoginResponseDto();
        user1LoginResponseDto.setId(user1.getId());
        user1LoginResponseDto.setUsername(user1.getRealUsername());
        user1LoginResponseDto.setEmail(user1.getEmail());
        user1LoginResponseDto.setToken("mock token");

        user2 = new User();
        user2.setId(2L);
        user2.setUsername("testUsername2");
        user2.setEmail("test@gmail2.com");
        user2.setPassword("test2Password#");
        user2.setFirstName("testFirstName");
        user2.setLastName("testLastName");
        user2.setProfilePicture(new byte[123]);
        user2.setAuthorities(roleSet);

        user2ResponseDto = new UserResponseDto();
        user2ResponseDto.setId(user2.getId());
        user2ResponseDto.setUsername(user2.getUsername());
        user2ResponseDto.setEmail(user2.getEmail());
        user2ResponseDto.setFirstName(user2.getFirstName());
        user2ResponseDto.setLastName(user2.getLastName());
        user2ResponseDto.setProfilePicture(user2.getProfilePicture());
        user2ResponseDto.setAuthorities(user2
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toSet())
        );

        tweet = new Tweet();
        tweet.setId(1L);
        tweet.setTweetText("testTweetText");
        tweet.setCreatedDate(LocalDateTime.now());
        tweet.setPicture(new byte[123]);
        tweet.setUser(user1);

        comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("testCommentText");
        comment.setCreatedDate(LocalDateTime.now());
        comment.setPicture(new byte[123]);
        comment.setUser(user2);
        comment.setTweet(tweet);

        like1 = new Like();
        like1.setId(1L);
        like1.setUser(user2);
        like1.setTweet(tweet);

        like2 = new Like();
        like2.setId(2L);
        like2.setUser(user1);
        like2.setComment(comment);

        retweet1 = new Retweet();
        retweet1.setId(1L);
        retweet1.setUser(user2);
        retweet1.setTweet(tweet);

        retweet2 = new Retweet();
        retweet2.setId(2L);
        retweet2.setUser(user1);
        retweet2.setComment(comment);

    }



    @DisplayName("registerUser, does user register")
    @Test
    void registerUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        when(authService.registerUser(user1RegisterRequestDto))
                .thenReturn(user1RegisterResponseDto);

        UserRegisterResponseDto foundUserRegisterResponseDto = authService.registerUser(user1RegisterRequestDto);

                mockMvc.perform(post("/auth/register/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user1RegisterRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user1RegisterResponseDto.getId()))
                .andExpect(jsonPath("$.username").value(user1RegisterResponseDto.getUsername()))
                .andExpect(jsonPath("$.email").value(user1RegisterResponseDto.getEmail()))
                ;

    }


    @DisplayName("loginUser, does user login")
    @Test
    void loginUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        when(authService.loginUser(user1LoginRequestDto))
                .thenReturn(user1LoginResponseDto);

        UserLoginResponseDto foundUserLoginResponseDto = authService.loginUser(user1LoginRequestDto);

        mockMvc.perform(post("/auth/login/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1LoginRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1LoginResponseDto.getId()))
                .andExpect(jsonPath("$.username").value(user1LoginResponseDto.getUsername()))
                .andExpect(jsonPath("$.email").value(user1LoginResponseDto.getEmail()))
                .andExpect(jsonPath("$.token").value(user1LoginResponseDto.getToken()))
        ;

    }


    @DisplayName("putUser, is user updated")
    @Test
    void putUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        when(authService.putUser(user1.getId(),user1RegisterRequestDto))
                .thenReturn(user1RegisterResponseDto);

        UserRegisterResponseDto foundUserRegisterResponseDto = authService.putUser(user1.getId(), user1RegisterRequestDto);

        mockMvc.perform(put("/auth/user/{id}", user1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1RegisterRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1RegisterResponseDto.getId()))
                .andExpect(jsonPath("$.username").value(user1RegisterResponseDto.getUsername()))
                .andExpect(jsonPath("$.email").value(user1RegisterResponseDto.getEmail()))
        ;

    }


    @DisplayName("patchUser, is user updated")
    @Test
    void patchUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        when(authService.patchUser(user1.getId(),user1RegisterRequestDto))
                .thenReturn(user1RegisterResponseDto);

        UserRegisterResponseDto foundUserRegisterResponseDto = authService.patchUser(user1.getId(), user1RegisterRequestDto);

        mockMvc.perform(patch("/auth/user/{id}", user1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1RegisterRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user1RegisterResponseDto.getId()))
                .andExpect(jsonPath("$.username").value(user1RegisterResponseDto.getUsername()))
                .andExpect(jsonPath("$.email").value(user1RegisterResponseDto.getEmail()))
        ;

    }


    @DisplayName("deleteUser, is user deleted")
    @Test
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/auth/user/{id}", user1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        ;

    }









}



