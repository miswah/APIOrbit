package com.miswah.apiorbit.repository;

import com.miswah.apiorbit.model.ActivityLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLogModel, Long> {
}
