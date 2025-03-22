package com.miswah.apiorbit.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "mock_api")
public class MockApiModel {

    @Id
    private int id;

    @Column(name = "api_version_id")
    private int apiVersionId;

    @Column(name = "mock_response")
    private String mockResponse;

    @Column(name = "created_by")
    private int createdBy;
}
