package com.miswah.apiorbit.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "api_definition_tag")
public class ApiDefinitionTagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "api_definition_id", nullable = false)
    private ApiDefinitionModel apiDefinitionId;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private TagModel tagId;
}
