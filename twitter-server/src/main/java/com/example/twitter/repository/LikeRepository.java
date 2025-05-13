package com.example.twitter.repository;

import com.example.twitter.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query(value =
            "SELECT l.* \n" +
            "FROM twit.likes AS l\n" +
            "INNER JOIN twit.users AS u\n" +
            "ON l.user_id = u.id\n" +
            "WHERE l.tweet_id = :tweetId AND u.email = :username",
            nativeQuery = true)
    Like getLikeOfTweetByTweetIdAndUsername(Long tweetId, String username);

    @Query(value =
            "SELECT l.* \n" +
            "FROM twit.likes AS l\n" +
            "INNER JOIN twit.users AS u\n" +
            "ON l.user_id = u.id\n" +
            "WHERE l.comment_id = :commentId AND u.email = :username",
            nativeQuery = true)
    Like getLikeOfCommentByCommentIdAndUsername(Long commentId, String username);



}
