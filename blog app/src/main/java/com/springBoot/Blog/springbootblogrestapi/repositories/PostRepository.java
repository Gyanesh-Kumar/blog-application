package com.springBoot.Blog.springbootblogrestapi.repositories;

import com.springBoot.Blog.springbootblogrestapi.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// entity type and primary key data type
public interface PostRepository extends JpaRepository<Post,Long> {
}
