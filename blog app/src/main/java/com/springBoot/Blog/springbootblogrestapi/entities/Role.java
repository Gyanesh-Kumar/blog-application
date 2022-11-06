package com.springBoot.Blog.springbootblogrestapi.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //size of role = 60
    @Column(length = 60)
    private String name;

}
