package com.example.twitter.repository;

import com.example.twitter.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value =
            "SELECT COUNT(comment_id)\n" +
            "FROM twit.comments AS c\n" +
            "INNER JOIN twit.likes AS l\n" +
            "ON c.id = l.comment_id\n" +
            "WHERE c.id = :id",
            nativeQuery = true)
    Integer getLikeCount(Long id);

    @Query(value =
            "SELECT COUNT(comment_id)\n" +
            "FROM twit.comments AS c\n" +
            "INNER JOIN twit.retweets AS r\n" +
            "ON c.id = r.comment_id\n" +
            "WHERE c.id = :id\n",
            nativeQuery = true)
    Integer getRetweetCount(Long id);
}
