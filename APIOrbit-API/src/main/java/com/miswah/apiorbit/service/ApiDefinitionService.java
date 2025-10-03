package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.ApiDefinitionRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ApiDefinitionService {

    List<ApiDefinitionResponseDto> getAllDefinitions();

    ApiDefinitionResponseDto getDefinitionById(UUID id);

    ApiDefinitionResponseDto createDefinition(ApiDefinitionRequestDto apiDefinitionRequestDto, String email);

    ApiDefinitionResponseDto updateDefinition(ApiDefinitionRequestDto apiDefinitionRequestDto, UUID id);

    ApiDefinitionResponseDto deleteDefinition(UUID id);
}
