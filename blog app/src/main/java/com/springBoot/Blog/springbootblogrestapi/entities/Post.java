package com.springBoot.Blog.springbootblogrestapi.entities;

//theory
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
// database me posts naam ke table se link
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)

//jpa entity creation of post
public class Post {

    //primary key
    @Id
    //to specify primary key generation strategy
    @GeneratedValue(
            strategy = GenerationType.IDENTITY//meaning read theory google
    )

    private Long id;

    @Column(name = "title" , nullable = false  )
    private String title;

    @Column(name = "description" , nullable = false  )
    private String description;

    @Column(name = "content" , nullable = false  )
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}