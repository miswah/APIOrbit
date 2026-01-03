package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.response.ActivityLogResponseDTO;
import com.miswah.apiorbit.model.ActivityLogModel;
import com.miswah.apiorbit.repository.ActivityLogRepository;
import com.miswah.apiorbit.service.ActivityLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    private final ActivityLogRepository repo;
    public ActivityLogServiceImpl(ActivityLogRepository repo) { this.repo = repo; }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(ActivityLogModel log) {
        repo.save(log);
    }

    @Override
    public List<ActivityLogResponseDTO> getLogs() {
        return this.mapToDtoList(this.repo.findAll());
    }

    private ActivityLogResponseDTO mapToDto(ActivityLogModel m){
        return new ActivityLogResponseDTO(m.getUserId(), m.getUserName(), m.getTimestamp(), m.getRemoteAddr(),
                m.getHttpMethod(), m.getPath(), m.getModuleName(), m.getActionName(), m.getOutcome(), m.getDurationMs(),
                m.getParams(), m.getTraceId());
    }

    private List<ActivityLogResponseDTO> mapToDtoList(List<ActivityLogModel> model){
        return model.stream().map(this::mapToDto).toList();
    }
}
