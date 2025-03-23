package com.miswah.apiorbit.model;


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

    @Column(name = "mock_response")
    private String mockResponse;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserModel createdBy;
}
