package com.example.twitter.service;

import com.example.twitter.entity.Comment;
import com.example.twitter.entity.Like;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.LikeNotFoundException;
import com.example.twitter.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{
    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository){
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> getALl() {
        return likeRepository.findAll();
    }

    @Override
    public Like getById(Long id) {
        return likeRepository
                .findById(id)
                .orElseThrow(()->new LikeNotFoundException("Like with " + id + " not found."));
    }

    @Override
    public Like save(Like like, Comment comment, Tweet tweet, User user) {
        if(tweet != null) tweet.addLike(like);
        if(comment != null) comment.addLikes(like);
        user.addLikes(like);
        return likeRepository.save(like);
    }

    @Override
    public Like put(Long id, Like like, Comment comment, Tweet tweet, User user) {
        Optional<Like> likeOptional = likeRepository.findById(id);
        if(likeOptional.isPresent()){
            like.setId(id);
            return likeRepository.save(like);
        }
        if(tweet != null) tweet.addLike(like);
        if(comment != null) comment.addLikes(like);
        user.addLikes(like);
        return likeRepository.save(like);
    }

    @Override
    public Like patch(Long id, Like like) {
        return like;
    }

    @Override
    public void delete(Long id) {
        likeRepository.deleteById(id);
    }
}
