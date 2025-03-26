package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.model.ApiDefinitionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiDefinitionRepository extends JpaRepository<ApiDefinitionModel, Long> {
    Optional<ApiDefinitionModel> findAllById(Long id);
}
