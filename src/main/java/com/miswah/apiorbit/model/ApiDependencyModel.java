package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.RelationTypes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_dependency")
public class ApiDependencyModel {

    @Id
    private int id;

    @Column(name = "source_api_version_id")
    private int sourceApiVersionId;

    @Column(name ="target_api_version_id")
    private int targetApiVersionId;

    @Column(name = "relation_types")
    private RelationTypes relationType;
}
