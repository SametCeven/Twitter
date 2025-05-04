package com.example.twitter.repository;

import com.example.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

    @Query(value =
            "SELECT t.user_id,t.id, t.tweet_text, t.created_date, t.picture\n" +
            "FROM twit.users AS u\n" +
            "INNER JOIN twit.tweets AS t\n" +
            "ON u.id = t.user_id\t\n" +
            "WHERE u.id = :id",nativeQuery = true)
    List<Tweet> getByUserId(Long id);

}
