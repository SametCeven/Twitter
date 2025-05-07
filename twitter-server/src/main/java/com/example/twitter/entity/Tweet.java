package com.example.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweets", schema = "twit")
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

    public void addComment(Comment comment){
        if(comments == null) comments = new ArrayList<>();
        comments.add(comment);
        comment.setTweet(this);
    }

    public void addLike(Like like){
        if(likes == null) likes = new ArrayList<>();
        likes.add(like);
        like.setTweet(this);
    }

    public void addRetweet(Retweet retweet){
        if(retweets == null) retweets = new ArrayList<>();
        retweets.add(retweet);
        retweet.setTweet(this);
    }

}
