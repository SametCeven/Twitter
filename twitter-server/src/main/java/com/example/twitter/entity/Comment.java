package com.example.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comments", schema = "twitter")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "picture")
    private String picture;

    @NotNull
    @NotEmpty
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @NotNull
    @NotEmpty
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "comment")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<Retweet> retweets;

    public void addLikes(Like like){
        likes.add(like);
    }

    public void addRetweet(Retweet retweet){
        retweets.add(retweet);
    }

}
