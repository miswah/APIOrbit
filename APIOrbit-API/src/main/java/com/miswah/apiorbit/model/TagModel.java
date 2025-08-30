package com.miswah.apiorbit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
