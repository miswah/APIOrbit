package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.ApiVersionRequestDto;
import com.miswah.apiorbit.dto.response.ApiVersionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ApiVersionService {
    String create(ApiVersionRequestDto apiVersionRequestDto);

    List<ApiVersionResponseDto> getByApiVersion(UUID apiDefinitionId);

    ApiVersionResponseDto updateById(UUID id, ApiVersionRequestDto dto);

    ApiVersionResponseDto deleteById(UUID id);
}
