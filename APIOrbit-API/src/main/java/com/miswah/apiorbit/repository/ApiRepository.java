package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.ResourceStatus;
import com.miswah.apiorbit.model.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApiRepository extends JpaRepository<ApiModel, UUID> {
    List<ApiModel> findByStatus(ResourceStatus status);
    long countByStatus(ResourceStatus status);
    long countByHttpMethod(HttpStatus httpStatus);
}
