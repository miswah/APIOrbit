package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.MockApiAdminRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import com.miswah.apiorbit.dto.response.MockApiAdminResponseDTO;
import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.model.MockApiModel;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.repository.MockApiRepository;
import com.miswah.apiorbit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
public class MockApiAdminServiceImpl implements MockApiAdminService {
    private final UserLookUpService userLookUpService;
    private final MockApiRepository mockApiRepository;
    private final ApiRepository apiRepository;


    @Autowired
    public MockApiAdminServiceImpl(UserLookUpService userLookUpService, MockApiRepository mockApiRepository, ApiRepository apiRepository){
        this.userLookUpService = userLookUpService;
        this.mockApiRepository = mockApiRepository;
        this.apiRepository = apiRepository;
    }

    @Override
    public MockApiAdminResponseDTO createMock(MockApiAdminRequestDTO mockApiRequestDTO, Principal principal){
        MockApiModel model = convertToModel(mockApiRequestDTO, principal.getName());
        this.mockApiRepository.save(model);
        return convertToMockObject(model);
    }


    private MockApiModel convertToModel(MockApiAdminRequestDTO mockApiRequestDTO, String email){
        MockApiModel model = new MockApiModel();
        model.setDelay(mockApiRequestDTO.delay());
        model.setCreatedBy(this.userLookUpService.getUserByEmail(email));
        model.setApiModel(this.apiRepository.findById(mockApiRequestDTO.apiId()).orElseThrow());
        model.setRequestBody(mockApiRequestDTO.schemaRequest());
        model.setResponseBody(mockApiRequestDTO.schemaResponse());
        return model;
    }

    private MockApiAdminResponseDTO convertToMockObject(MockApiModel model){
        return new MockApiAdminResponseDTO(this.convertToDto(model.getApiModel()));
    }



    private ApiResponseDTO convertToDto(ApiModel model){
        ApiResponseDTO dto = new ApiResponseDTO();
        dto.setId(model.getId());
        dto.setCategory(model.getCategory());
        dto.setStatus(model.getStatus());
        dto.setDocumentationUrl(model.getDocumentationUrl());
        dto.setMockUrl(model.getMockUrl());
        dto.setApprovedBy(model.getApprovedBy());
        dto.setCreatedBy(model.getCreatedBy());
        dto.setUpdatedDate(model.getUpdatedDate());
        dto.setInstructions(model.getInstructions());
        dto.setName(model.getName());
        dto.setDescription(model.getDescriptions());
        dto.setTags(model.getTags());
        dto.setUrlBase(model.getBaseUrl());
        dto.setAuthType(model.getAuthType());
        dto.setHttpMethod(model.getHttpMethod());
        return dto;
    }
}
