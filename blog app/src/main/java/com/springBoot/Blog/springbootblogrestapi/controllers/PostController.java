package com.springBoot.Blog.springbootblogrestapi.controllers;

import com.springBoot.Blog.springbootblogrestapi.payload.PostDto;
import com.springBoot.Blog.springbootblogrestapi.payload.PostResponse;
import com.springBoot.Blog.springbootblogrestapi.services.PostService;
import com.springBoot.Blog.springbootblogrestapi.utilities.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest APIs for Post resources")
@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post rest api
    //@RequestBody uses dependency jackson databind  deserializes the JSON into a Java type object(postDto)
    //ResponseEntity - represents the whole HTTP response: status code, headers, and body
    // only admin can access
    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        PostDto postDto1 = postService.createPost(postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Posts REST API")
    // get all posts rest api
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            //@RequestParam to extract query parameters, form parameters, and even files from the request.
            //attributes: name, value, required, and defaultValue.
            //required = false means it is optional
            //first step for pagination
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize ,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return postService.getAllPosts(pageNo, pageSize , sortBy, sortDir);
    }

    // get post by id
    //Path Variable is used to bind the uri variable with variable in parameter
    @ApiOperation(value = "Get Post By Id REST API")
    @GetMapping(value = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post by id rest api
    @ApiOperation(value = "Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
