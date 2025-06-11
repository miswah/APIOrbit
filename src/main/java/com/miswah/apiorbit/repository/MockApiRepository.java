package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.model.MockApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockApiRepository extends JpaRepository<MockApiModel, Long> {

}
