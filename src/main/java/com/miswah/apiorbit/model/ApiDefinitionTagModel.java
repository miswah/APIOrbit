package com.miswah.apiorbit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_definition_tag")
public class ApiDefinitionTagModel {

    @Id
    private int id;

    @Column(name = "api_definition_id")
    private int apiDefinitionId;

    @Column(name = "tag_id")
    private int tagId;
}
