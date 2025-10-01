package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.model.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiRepository extends JpaRepository<ApiModel, Long> {
    List<ApiModel> findByStatus(ApiStatus status);
}
