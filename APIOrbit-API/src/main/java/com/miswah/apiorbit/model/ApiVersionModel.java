package com.miswah.apiorbit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miswah.apiorbit.enums.ResourceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "api_version", uniqueConstraints = {@UniqueConstraint(columnNames={"version", "api_definition_id"})})
@Data
public class ApiVersionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "version")
    private float version;

    @ManyToOne
    @JoinColumn(name = "api_definition_id", nullable = false)
    @JsonIgnore
    private ApiDefinitionModel apiDefinitionModel;

    @ManyToOne
    @JoinColumn(name = "dependency_id", nullable = true)
    private ApiDependencyModel apiDependencyModel;

    @Column(name = "status")
    private ResourceStatus status;

    @Column(name = "schema_request")
    private String schemaRequest;

    @Column(name = "schema_response")
    private String schemaResponse;
}
