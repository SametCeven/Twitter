package com.example.twitter.repository;

import com.example.twitter.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

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

        user2 = new User();
        user2.setId(2L);
        user2.setUsername("testUsername2");
        user2.setEmail("test@gmail2.com");
        user2.setPassword("test2Password#");
        user2.setFirstName("testFirstName");
        user2.setLastName("testLastName");
        user2.setProfilePicture(new byte[123]);
        user2.setAuthorities(roleSet);

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


    @DisplayName("findUserByEmail, does user exist and can it return the correct user")
    @Test
    void findUserByEmail(){

        when(userRepository.findUserByEmail(user1.getEmail()))
                .thenReturn(Optional.of(user1));

        Optional<User> foundUser = userRepository.findUserByEmail(user1.getEmail());

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(user1.getId(), foundUser.get().getId());
        Assertions.assertEquals(user1.getRealUsername(), foundUser.get().getRealUsername());
        Assertions.assertEquals(user1.getEmail(), foundUser.get().getEmail());
        Assertions.assertEquals(user1.getPassword(), foundUser.get().getPassword());
        Assertions.assertEquals(user1.getFirstName(), foundUser.get().getFirstName());
        Assertions.assertEquals(user1.getLastName(), foundUser.get().getLastName());
        Assertions.assertEquals(user1.getProfilePicture(), foundUser.get().getProfilePicture());
        Assertions.assertEquals(user1.getUsername(), foundUser.get().getUsername());
    }

    @DisplayName("findUserByUsername, does user exist and can it return the correct user")
    @Test
    void findUserByUsername(){

        when(userRepository.findUserByUsername(user1.getRealUsername()))
                .thenReturn(Optional.of(user1));

        Optional<User> foundUser = userRepository.findUserByUsername(user1.getRealUsername());

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(user1.getId(), foundUser.get().getId());
        Assertions.assertEquals(user1.getRealUsername(), foundUser.get().getRealUsername());
        Assertions.assertEquals(user1.getEmail(), foundUser.get().getEmail());
        Assertions.assertEquals(user1.getPassword(), foundUser.get().getPassword());
        Assertions.assertEquals(user1.getFirstName(), foundUser.get().getFirstName());
        Assertions.assertEquals(user1.getLastName(), foundUser.get().getLastName());
        Assertions.assertEquals(user1.getProfilePicture(), foundUser.get().getProfilePicture());
        Assertions.assertEquals(user1.getUsername(), foundUser.get().getUsername());
    }

    @DisplayName("findUsersAuthorities, do roles of user returns correctly")
    @Test
    void findUsersAuthorities(){

        Set<String> roles = user1
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toSet());

        when(userRepository.findUsersAuthorities(user1.getId()))
                .thenReturn(roles);

        Set<String> foundRoles = userRepository.findUsersAuthorities(user1.getId());

        Assertions.assertEquals(roles, foundRoles);
    }




}
