package com.miswah.apiorbit.model;

import com.miswah.apiorbit.enums.RelationTypes;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "api_dependency")
public class ApiDependencyModel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "source_api_version_id", nullable = false)
    private ApiVersionModel sourceApiVersionId;

    @OneToOne
    @JoinColumn(name = "target_api_version_id", nullable = false)
    private ApiVersionModel targetApiVersionId;

    @Column(name = "relation_types")
    private RelationTypes relationType;
}
