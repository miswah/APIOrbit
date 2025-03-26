package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.AuthTypeNames;
import com.miswah.apiorbit.enums.HttpMethods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_definition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDefinitionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_path")
    private String path;

    @Column(name ="http_method")
    private HttpMethods httpMethod;

    @Column(name = "description")
    private String description;

    @Column(name = "auth_type")
    private AuthTypeNames authType;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel project;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel createdBy;

}
