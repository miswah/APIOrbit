package com.miswah.apiorbit.model;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "test_request")
public class TestRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "api_version_id", nullable = false)
    private ApiVersionModel apiVersionId;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "request_by", nullable = false)
    private UserModel requestBy;
}
