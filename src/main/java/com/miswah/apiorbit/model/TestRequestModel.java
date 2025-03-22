package com.miswah.apiorbit.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "test_request")
public class TestRequestModel {

    @Id
    private int id;

    @Column(name = "api_version_id")
    private int apiVersionId;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "response_body")
    private String responseBody;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "request_by")
    private int requestBy;
}
