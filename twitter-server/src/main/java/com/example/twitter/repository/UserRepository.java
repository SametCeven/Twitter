package com.example.twitter.repository;

import com.example.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Query(value =
            "SELECT r.authority\n" +
            "FROM twit.users as u\n" +
            "LEFT JOIN twit.users_roles as ur\n" +
            "ON u.id = ur.user_id\n" +
            "LEFT JOIN twit.roles as r\n" +
            "ON ur.role_id = r.id\t\n" +
            "WHERE u.id = :id",
            nativeQuery = true)
    Set<String> findUsersAuthorities(Long id);

}
