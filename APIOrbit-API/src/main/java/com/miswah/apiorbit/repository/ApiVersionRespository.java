package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.model.ApiVersionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiVersionRespository extends JpaRepository<ApiVersionModel, UUID> {
}
