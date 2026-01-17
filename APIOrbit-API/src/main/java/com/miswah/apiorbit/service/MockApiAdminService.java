package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.MockApiAdminRequestDTO;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import com.miswah.apiorbit.dto.response.MockApiAdminResponseDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface MockApiAdminService {

    MockApiAdminResponseDTO createMock(MockApiAdminRequestDTO mockApiRequestDTO, Principal principal);
}
