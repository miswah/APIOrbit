package com.miswah.apiorbit.model;

import jakarta.persistence.*;

@Entity
@Table(schema = "project", name = "project")
public class ProjectModel {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_id")
    private int user_id;
}
