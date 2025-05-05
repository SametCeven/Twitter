package com.example.twitter.repository;

import com.example.twitter.dto.CommentResponseDto;
import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

    @Query(value =
            "SELECT t.*\n" +
            "FROM twit.users AS u\n" +
            "INNER JOIN twit.tweets AS t\n" +
            "ON u.id = t.user_id\t\n" +
            "WHERE u.id = :id",
            nativeQuery = true)
    List<Tweet> getByUserId(Long id);


    @Query(value =
            "SELECT c.*\n" +
            "FROM twit.tweets AS t\n" +
            "INNER JOIN twit.comments AS c\n" +
            "ON t.id = c.tweet_id\n" +
            "WHERE t.id = :id",
            nativeQuery = true)
    List<Comment> getCommentByTweetId(Long id);

    @Query(value =
            "SELECT COUNT(tweet_id)\n" +
                    "FROM twit.tweets AS t\n" +
                    "INNER JOIN twit.likes AS l\n" +
                    "ON t.id = l.tweet_id\n" +
                    "WHERE t.id = :id",
            nativeQuery = true)
    Integer getLikeCount(Long id);

    @Query(value =
            "SELECT COUNT(tweet_id)\n" +
                    "FROM twit.tweets AS t\n" +
                    "INNER JOIN twit.retweets AS r\n" +
                    "ON t.id = r.tweet_id\n" +
                    "WHERE t.id = :id\n",
            nativeQuery = true)
    Integer getRetweetCount(Long id);



}
