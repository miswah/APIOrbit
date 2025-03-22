package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.HttpMethods;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_definition")
public class ApiDefinitionModel {

    @Id
    private int id;

    @Column(name = "url_path")
    private String path;

    @Column(name ="http_method")
    private HttpMethods httpMethod;

    @Column(name = "description")
    private String description;

    @Column(name = "auth_type_id")
    private int authTypeId;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "created_by")
    private int createdBy;
}
