package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.enums.AuthTypeNames;
import com.miswah.apiorbit.enums.HttpMethods;
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

    @Column(name = "status")
    private ApiStatus status;

    @Column(name ="documentation_url")
    private String documentationUrl;

    @Column(name = "mock_url")
    private String mockUrl;

    @Column(name = "approved_by")
    private String approvedBy;
}
