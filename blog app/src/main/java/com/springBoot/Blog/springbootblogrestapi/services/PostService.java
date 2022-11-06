package com.springBoot.Blog.springbootblogrestapi.services;

import com.springBoot.Blog.springbootblogrestapi.payload.PostDto;
import com.springBoot.Blog.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    //2nd step for pagination
    PostResponse getAllPosts(int pageNo, int pageSize , String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
