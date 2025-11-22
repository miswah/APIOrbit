package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.model.DocumentationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface DocumentationRepository extends JpaRepository<DocumentationModel, UUID> {
    Optional<DocumentationModel> findByApi_Id(UUID apiId);
}
