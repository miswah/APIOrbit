package com.miswah.apiorbit.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tag")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
