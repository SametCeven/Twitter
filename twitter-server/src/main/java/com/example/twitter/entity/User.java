package com.example.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users", schema = "twit")
public class User implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            schema = "twit",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

    public void addTweet(Tweet tweet){
        if(tweets == null) tweets = new ArrayList<>();
        tweets.add(tweet);
        tweet.setUser(this);
    }

    public void addComment(Comment comment){
        if(comments == null) comments = new ArrayList<>();
        comments.add(comment);
        comment.setUser(this);
    }

    public void addLikes(Like like){
        if(likes == null) likes = new ArrayList<>();
        likes.add(like);
        like.setUser(this);
    }

    public void addRetweet(Retweet retweet){
        if(retweets == null) retweets = new ArrayList<>();
        retweets.add(retweet);
        retweet.setUser(this);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString(){
        return "User";
    }
}
