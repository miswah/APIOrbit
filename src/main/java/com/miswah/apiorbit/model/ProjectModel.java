package com.miswah.apiorbit.model;

import jakarta.persistence.*;

@Entity
@Table(schema = "project", name = "project")
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserModel user_id;
}
