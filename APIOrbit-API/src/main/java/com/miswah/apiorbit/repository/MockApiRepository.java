package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.model.ApiDefinitionModel;
import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.model.ApiVersionModel;
import com.miswah.apiorbit.model.MockApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MockApiRepository extends JpaRepository<MockApiModel, UUID> {

//    Optional<MockApiModel> findByApiDefinitionModel(ApiDefinitionModel id);
//    Optional<MockApiModel> findByApiDefinitionModelAndHttpMethods(ApiDefinitionModel model, HttpMethods httpMethods);

    Optional<MockApiModel> findByApiModel(ApiModel apiMode);
}
