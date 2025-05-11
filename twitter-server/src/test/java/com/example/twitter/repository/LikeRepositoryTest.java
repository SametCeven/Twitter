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
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeRepositoryTest {

    @Mock
    private LikeRepository likeRepository;

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


    @DisplayName("getLikeOfTweetByTweetIdAndUsername, can it return correct like of a tweet given tweed id and username")
    @Test
    void getLikeOfTweetByTweetIdAndUsername(){

        when(likeRepository.getLikeOfTweetByTweetIdAndUsername(tweet.getId(), user1.getUsername()))
                .thenReturn(like1);

        Like foundLike = likeRepository.getLikeOfTweetByTweetIdAndUsername(tweet.getId(), user1.getUsername());

        Assertions.assertEquals(foundLike.getId(), like1.getId());
        Assertions.assertEquals(foundLike.getUser(), like1.getUser());
        Assertions.assertEquals(foundLike.getTweet(), like1.getTweet());

    }


    @DisplayName("getLikeOfCommentByCommentIdAndUsername, can it return correct like of a comment given comment id and username")
    @Test
    void getLikeOfCommentByCommentIdAndUsername(){

        when(likeRepository.getLikeOfCommentByCommentIdAndUsername(comment.getId(), user1.getUsername()))
                .thenReturn(like2);

        Like foundLike = likeRepository.getLikeOfCommentByCommentIdAndUsername(comment.getId(), user1.getUsername());

        Assertions.assertEquals(foundLike.getId(), like2.getId());
        Assertions.assertEquals(foundLike.getUser(), like2.getUser());
        Assertions.assertEquals(foundLike.getComment(), like2.getComment());

    }























}
