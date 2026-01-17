package com.miswah.apiorbit.service;


import com.miswah.apiorbit.dto.response.ApiByMethodCountResponse;
import com.miswah.apiorbit.dto.response.SystemKPIResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DashboardService {
    public SystemKPIResponseDTO getSystemKPI();

    public List<ApiByMethodCountResponse> getApiCountByMethod();
}
