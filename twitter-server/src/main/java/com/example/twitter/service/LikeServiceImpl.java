package com.example.twitter.service;

import com.example.twitter.dto.LikeCommentRequestDto;
import com.example.twitter.dto.LikeCommentResponseDto;
import com.example.twitter.dto.LikeTweetRequestDto;
import com.example.twitter.dto.LikeTweetResponseDto;
import com.example.twitter.entity.Like;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.LikeExistsException;
import com.example.twitter.exceptions.UserNotFoundException;
import com.example.twitter.repository.LikeRepository;
import com.example.twitter.repository.UserRepository;
import com.example.twitter.utils.DtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl implements LikeService{

    private DtoMapping dtoMapping;
    private UserRepository userRepository;
    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(DtoMapping dtoMapping, UserRepository userRepository, LikeRepository likeRepository){
        this.dtoMapping = dtoMapping;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }


    @Override
    public LikeTweetResponseDto saveTweet(LikeTweetRequestDto likeTweetRequestDto, String username) {
        if(likeRepository.getLikeOfTweetByTweetIdAndUsername(likeTweetRequestDto.getTweetId(), username) != null){
            throw new LikeExistsException("Already liked.");
        }
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Like like = dtoMapping.MappingLikeTweetRequestToLike(likeTweetRequestDto);
        like.setUser(user);
        likeRepository.save(like);
        return dtoMapping.MappingLikeToLikeTweetResponseDto(like);
    }

    @Override
    public LikeTweetResponseDto removeTweet(LikeTweetRequestDto likeTweetRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Like like = likeRepository.getLikeOfTweetByTweetIdAndUsername(likeTweetRequestDto.getTweetId(), username);
        like.setUser(user);
        likeRepository.deleteById(like.getId());
        return dtoMapping.MappingLikeToLikeTweetResponseDto(like);
    }

    @Override
    public Boolean isTweetLiked(Long tweetId, String username) {
        if(likeRepository.getLikeOfTweetByTweetIdAndUsername(tweetId, username) != null){
            return true;
        }
        return false;
    }

    @Override
    public LikeCommentResponseDto saveComment(LikeCommentRequestDto likeCommentRequestDto, String username) {
        if(likeRepository.getLikeOfCommentByCommentIdAndUsername(likeCommentRequestDto.getCommentId(), username) != null){
            throw new LikeExistsException("Already liked.");
        }
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Like like = dtoMapping.MappingLikeCommentRequestToLike(likeCommentRequestDto);
        like.setUser(user);
        likeRepository.save(like);
        return dtoMapping.MappingLikeToLikeCommentResponseResponseDto(like);
    }

    @Override
    public LikeCommentResponseDto removeComment(LikeCommentRequestDto likeCommentRequestDto, String username) {
        User user = userRepository
                .findUserByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Like like = likeRepository.getLikeOfCommentByCommentIdAndUsername(likeCommentRequestDto.getCommentId(), username);
        like.setUser(user);
        likeRepository.deleteById(like.getId());
        return dtoMapping.MappingLikeToLikeCommentResponseResponseDto(like);
    }


}
