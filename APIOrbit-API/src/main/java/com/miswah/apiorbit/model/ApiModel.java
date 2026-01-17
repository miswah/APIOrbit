package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.AuthTypeNames;
import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.enums.ResourceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="apis")
public class ApiModel extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "tags", nullable = true)
    private ApiDefinitionTagModel apiDefinitionTagModel;

    @Column(name = "singleLineTags")
    private String tags;

    @Column(name = "status")
    private ResourceStatus status;

    @Column(name ="documentation_url")
    private String documentationUrl;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "auth_type")
    private AuthTypeNames authType;

    @Column(name = "mock_url")
    private String mockUrl;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "name")
    private String name;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "http_method")
    private HttpMethods httpMethod;
}
