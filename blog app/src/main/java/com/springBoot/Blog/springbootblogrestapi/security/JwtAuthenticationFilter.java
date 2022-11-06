package com.springBoot.Blog.springbootblogrestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//OncePerRequestFilter - filter base class that aims to guarantee single execution per request dispatch of any servlet container
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // inject dependencies
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // get JWT (token) from http request
        String token = getJWTfromRequest(request);
        // validate token
        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
            // get username from token
            String username = tokenProvider.getUsernameFromJWT(token);
            // load user associated with token
            //UserDetails has a method to get username from db
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // set spring security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    //token contains this string
    // Bearer <accessToken>
    //extract this string from token
    private String getJWTfromRequest(HttpServletRequest request){
        //get header from request
        String bearerToken = request.getHeader("Authorization");
        //condition if token is empty or valid or not
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            //extracting token
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
