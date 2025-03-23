package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.Roles;
import jakarta.persistence.*;

@Entity
@Table(schema = "user", name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Roles role;
}
