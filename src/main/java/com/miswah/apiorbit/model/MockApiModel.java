package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.HttpMethods;
import jakarta.persistence.*;


@Entity
@Table(name = "mock_api")
public class MockApiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "api_version_id", nullable = false)
    private ApiVersionModel apiVersionId;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserModel createdBy;

    @Column(name = "http_method", nullable = false)
    private HttpMethods httpMethods;

    @OneToMany
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel project_id;

    @OneToOne
    @JoinColumn(name = "api_version_id", nullable = false)
    private ApiVersionModel api_version_id;

    @Column(name="delay", nullable = true)
    private int delay;
}
