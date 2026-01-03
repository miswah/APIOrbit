package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.response.ActivityLogResponseDTO;
import com.miswah.apiorbit.model.ActivityLogModel;

import java.util.List;

public interface ActivityLogService {
    public void save(ActivityLogModel log);
    public List<ActivityLogResponseDTO> getLogs();
}
