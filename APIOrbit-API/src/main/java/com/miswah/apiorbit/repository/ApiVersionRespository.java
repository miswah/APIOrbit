package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.ResourceStatus;
import com.miswah.apiorbit.model.ApiDefinitionModel;
import com.miswah.apiorbit.model.ApiVersionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiVersionRespository extends JpaRepository<ApiVersionModel, UUID> {
    Optional<List<ApiVersionModel>> findByApiDefinitionModelAndStatus(ApiDefinitionModel model, ResourceStatus status);
}
