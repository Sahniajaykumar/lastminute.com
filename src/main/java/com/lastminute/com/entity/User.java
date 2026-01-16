package com.lastminute.com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "username", nullable = false, unique = true)
    private String username;

    @Column(name= "email", nullable = false, unique = true)
    private String email;

    @Column(name= "password")
    private String password;

}
