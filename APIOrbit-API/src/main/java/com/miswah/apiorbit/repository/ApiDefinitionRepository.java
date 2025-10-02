package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.model.ApiDefinitionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApiDefinitionRepository extends JpaRepository<ApiDefinitionModel, UUID> {
    Optional<ApiDefinitionModel> findAllById(UUID id);
    Optional<ApiDefinitionModel> findByPathAndHttpMethod(String path, HttpMethods httpMethods);
}
