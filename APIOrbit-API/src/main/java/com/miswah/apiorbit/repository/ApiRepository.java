package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.model.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<ApiModel, Long> {
    List<ApiModel> findByStatus(ApiStatus status);
}
