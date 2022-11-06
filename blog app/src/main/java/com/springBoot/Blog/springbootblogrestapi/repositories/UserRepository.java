package com.springBoot.Blog.springbootblogrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springBoot.Blog.springbootblogrestapi.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //retrieve user by column email
    Optional<User> findByEmail(String email);

    //retrieve user by column username or email
    Optional<User> findByUsernameOrEmail(String username, String email);

    //retrieve user by column username
    Optional<User> findByUsername(String username);

    //check whether user exist by username
    Boolean existsByUsername(String username);

    //check whether user exist by email
    Boolean existsByEmail(String email);
}
