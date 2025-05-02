package com.example.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users", schema = "twitter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 5,max = 100)
    @Column(name = "username")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 150)
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 50)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$")
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Tweet> tweets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Retweet> retweets;

    public void addTweet(Tweet tweet){
        tweets.add(tweet);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void addLikes(Like like){
        likes.add(like);
    }

    public void addRetweet(Retweet retweet){
        retweets.add(retweet);
    }



}
