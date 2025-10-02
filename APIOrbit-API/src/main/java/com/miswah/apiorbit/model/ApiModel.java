package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.enums.AuthTypeNames;
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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "tags")
    private String tags;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "version")
    private double version;

    @Column(name = "status")
    private ApiStatus status;

    @Column(name = "auth_Type")
    private AuthTypeNames authTypeNames;

    @Column(name ="documentation_url")
    private String documentationUrl;

    @Column(name = "mock_url")
    private String mockUrl;

    @Column(name = "approved_by")
    private String approvedBy;
}
