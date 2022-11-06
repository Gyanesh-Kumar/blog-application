package com.springBoot.Blog.springbootblogrestapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//swagger internally uses curl to call the rest api's
//we get jwt token as a response of signin rest api
//once we get the jwt token we are going to use this jwt token int all the request to call api's
//if we don't use token then our springboot app will reject the request
//Configuration - if we want to make any java class as spring configuration class


@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    //api key for swagger
    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot Blog REST APIs",
                "Spring Boot Blog REST API Documentation",
                "1",
                "Terms of service",
                new Contact("Gyanesh Kumar",
                        "www.linkedin.com/in/gyanesh-kumar-8b61b3215",
                        "gyanesh.singh.5895@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }


    //docket instance
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                //configure api's from where swagger need to refer
                //where swagger should look for the rest api's
                .apis(RequestHandlerSelectors.any())
                //swagger will look for all the paths that we configure here
                .paths(PathSelectors.any())
                .build();
    }

    // need to add securityContext to docket object
    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    //typical configuration swagger uses to enable authorization on swagger ui
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}

