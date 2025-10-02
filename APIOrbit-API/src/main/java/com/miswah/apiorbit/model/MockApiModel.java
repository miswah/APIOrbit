package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.HttpMethods;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "mock_api", uniqueConstraints = {@UniqueConstraint(columnNames = {"api_definition_id", "http_method"})})
@Data
public class MockApiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "api_version_id", nullable = false)
    private ApiVersionModel apiVersionModel;

    @ManyToOne
    @JoinColumn(name = "api_definition_id", nullable = false)
    private ApiDefinitionModel apiDefinitionModel;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserModel createdBy;

    @Column(name = "http_method", nullable = false)
    private HttpMethods httpMethods;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel projectModel;

    @Column(name="delay", nullable = true)
    private int delay;
}
