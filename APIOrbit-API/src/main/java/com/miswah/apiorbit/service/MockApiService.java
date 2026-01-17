package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.response.MockApiResponseDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface MockApiService {
    public MockApiResponseDTO getMockByApiId(UUID id);
}
