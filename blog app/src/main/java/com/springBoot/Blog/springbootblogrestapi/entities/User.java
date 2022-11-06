package com.springBoot.Blog.springbootblogrestapi.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
//make this class as jpa entity
@Entity
//uniqueConstraints is used for variables which are unique for a user
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    //id - to specify the primary key
    //GeneratedValue - to specify primary key generation strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    //EAGER - whenever we fetch user entity we will fetch its roles also
    //LAZY - fetch roles on demand
    //cascade - whenever we perform cascade operations on parent , child also performs the cascade operations
    //here user is a parent , role is a child
    //eg - if i save and remove user , along with user role also will be saved or removed respectively .
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //JoinTable - join 2 table and create 3rd table
    //3rd table name is user_roles
    //primary key id becomes foreign key in user_roles table and hence we are referencing it using referencedColumnName
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    //for many to many mapping bw user and roles
    private Set<Role> roles;
}
