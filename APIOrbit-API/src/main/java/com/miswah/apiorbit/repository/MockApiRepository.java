package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.model.ApiDefinitionModel;
import com.miswah.apiorbit.model.ApiVersionModel;
import com.miswah.apiorbit.model.MockApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MockApiRepository extends JpaRepository<MockApiModel, Long> {

    Optional<MockApiModel> findByApiDefinitionModel(ApiDefinitionModel id);
    Optional<MockApiModel> findByApiDefinitionModelAndHttpMethods(ApiDefinitionModel model, HttpMethods httpMethods);
}
