package com.example.twitter.repository;

import com.example.twitter.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RetweetRepository extends JpaRepository<Retweet,Long> {

    @Query(value =
            "SELECT r.* \n" +
                    "FROM twit.retweets AS r\n" +
                    "INNER JOIN twit.users AS u\n" +
                    "ON r.user_id = u.id\n" +
                    "WHERE r.tweet_id = :tweetId AND u.username = :username",
            nativeQuery = true)
    Retweet getRetweetOfTweetByTweetIdAndUsername(Long tweetId, String username);
}
