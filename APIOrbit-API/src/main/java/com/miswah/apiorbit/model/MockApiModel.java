package com.miswah.apiorbit.model;


import com.miswah.apiorbit.enums.HttpMethods;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
//@Table(name = "mock_api", uniqueConstraints = {@UniqueConstraint(columnNames = {"api_version_id", "http_method"})})
@Table(name = "mock_api")
@Data
public class MockApiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name ="api_id", nullable = false)
    private ApiModel apiModel;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserModel createdBy;

    @Column(name="delay", nullable = true)
    private int delay;

    @Column(name = "request_body", nullable = true,columnDefinition="LONGTEXT")
    private String requestBody;

    @Column(name = "response_body", nullable = false,columnDefinition="LONGTEXT")
    private String responseBody;
}
