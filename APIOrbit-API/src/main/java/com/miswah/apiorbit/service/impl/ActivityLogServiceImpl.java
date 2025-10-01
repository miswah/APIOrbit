package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.model.ActivityLogModel;
import com.miswah.apiorbit.repository.ActivityLogRepository;
import com.miswah.apiorbit.service.ActivityLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository repo;
    public ActivityLogServiceImpl(ActivityLogRepository repo) { this.repo = repo; }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(ActivityLogModel log) {
        repo.save(log);
    }
}
