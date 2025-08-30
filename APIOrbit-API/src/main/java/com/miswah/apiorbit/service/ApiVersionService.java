package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.ApiVersionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ApiVersionService {
    String create(ApiVersionRequestDto apiVersionRequestDto);
}
