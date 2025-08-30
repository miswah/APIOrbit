package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.MockApiRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface MockApiAdminService {

    ApiDefinitionResponseDto createMock(MockApiRequestDto mockApiRequestDTO, Principal principal);
}
