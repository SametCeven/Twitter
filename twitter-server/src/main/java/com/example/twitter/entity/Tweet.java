package com.example.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweets", schema = "twitter")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "tweet_text")
    private String tweetText;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "picture")
    private byte[] picture;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tweet")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    private List<Retweet> retweets;

}
