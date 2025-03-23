package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.HttpMethods;
import jakarta.persistence.*;

@Entity
@Table(name = "api_definition")
public class ApiDefinitionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url_path")
    private String path;

    @Column(name ="http_method")
    private HttpMethods httpMethod;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "auth_type_id", nullable = false)
    private AuthTypeModel authTypeId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel projectId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel createdBy;

}
