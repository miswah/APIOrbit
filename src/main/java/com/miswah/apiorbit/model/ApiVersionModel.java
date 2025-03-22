package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.ApiStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_version")
public class ApiVersionModel {

    @Id
    private int id;

    @Column(name = "version")
    private float version;

    @Column(name = "api_definition_id")
    private int apiDefinitionId;

    @Column(name = "status")
    private ApiStatus status;

    @Column(name = "schema_request")
    private String schemaRequest;

    @Column(name = "schema_response")
    private String schemaResponse;
}
