package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.ApiStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "api_version")
@Data
public class ApiVersionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "version")
    private float version;

    @OneToOne
    @JoinColumn(name = "api_definition_id", nullable = false)
    private ApiDefinitionModel apiDefinitionModel;

    @Column(name = "status")
    private ApiStatus status;

    @Column(name = "schema_request")
    private String schemaRequest;

    @Column(name = "schema_response")
    private String schemaResponse;
}
