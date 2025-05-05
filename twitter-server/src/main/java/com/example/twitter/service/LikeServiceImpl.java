package com.example.twitter.service;

import com.example.twitter.dto.LikeRequestDto;
import com.example.twitter.dto.LikeResponseDto;
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
    public LikeResponseDto save(LikeRequestDto likeRequestDto, String username) {
        if(likeRepository.getLikeOfTweetByTweetIdAndUsername(likeRequestDto.getTweetId(), username) != null){
            throw new LikeExistsException("Already liked.");
        }
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Like like = dtoMapping.MappingLikeRequestToLike(likeRequestDto);
        like.setUser(user);
        likeRepository.save(like);
        return dtoMapping.MappingLikeToLikeResponseDto(like);
    }

    @Override
    public LikeResponseDto remove(LikeRequestDto likeRequestDto, String username) {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User with username: " + username + " not found."));
        Like like = likeRepository.getLikeOfTweetByTweetIdAndUsername(likeRequestDto.getTweetId(), username);
        like.setUser(user);
        likeRepository.deleteById(like.getId());
        return dtoMapping.MappingLikeToLikeResponseDto(like);
    }


}
