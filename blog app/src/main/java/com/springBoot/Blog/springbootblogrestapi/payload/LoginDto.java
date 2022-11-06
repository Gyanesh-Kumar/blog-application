package com.springBoot.Blog.springbootblogrestapi.payload;


import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
