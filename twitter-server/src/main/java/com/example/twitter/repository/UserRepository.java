package com.example.twitter.repository;

import com.example.twitter.entity.Role;
import com.example.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<Role> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<Role> findUserByUsername(String username);


}
