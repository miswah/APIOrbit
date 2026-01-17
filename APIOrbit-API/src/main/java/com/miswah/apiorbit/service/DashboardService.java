package com.miswah.apiorbit.service;


import com.miswah.apiorbit.dto.response.SystemKPIResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    public SystemKPIResponseDTO getSystemKPI();
}
