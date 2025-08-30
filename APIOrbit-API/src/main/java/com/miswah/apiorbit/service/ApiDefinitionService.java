package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.ApiDefinitionRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiDefinitionService {

    List<ApiDefinitionResponseDto> getAllDefinitions();

    ApiDefinitionResponseDto getDefinitionById(Long id);

    ApiDefinitionResponseDto createDefinition(ApiDefinitionRequestDto apiDefinitionRequestDto, String email);

    ApiDefinitionResponseDto updateDefinition(ApiDefinitionRequestDto apiDefinitionRequestDto, Long id);

    ApiDefinitionResponseDto deleteDefinition(Long id);
}
