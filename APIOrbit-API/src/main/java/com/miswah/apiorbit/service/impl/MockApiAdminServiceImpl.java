package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.MockApiRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import com.miswah.apiorbit.model.MockApiModel;
import com.miswah.apiorbit.repository.MockApiRepository;
import com.miswah.apiorbit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
public class MockApiAdminServiceImpl implements MockApiAdminService {
    private final ProjectLookUpService projectLookUpService;
    private final UserLookUpService userLookUpService;
    private final ApiDefinitionLookUpService apiDefinitionLookUpService;
    private final MockApiRepository mockApiRepository;
    private final ApiVersionLookUpService apiVersionLookUpService;


    @Autowired
    public MockApiAdminServiceImpl(ProjectLookUpService projectLookUpService, UserLookUpService userLookUpService, ApiDefinitionLookUpService apiDefinitionLookUpService, MockApiRepository mockApiRepository, ApiVersionLookUpService apiVersionLookUpService){
        this.projectLookUpService = projectLookUpService;
        this.userLookUpService = userLookUpService;
        this.apiDefinitionLookUpService = apiDefinitionLookUpService;
        this.mockApiRepository = mockApiRepository;
        this.apiVersionLookUpService = apiVersionLookUpService;
    }

    @Override
    public ApiDefinitionResponseDto createMock(MockApiRequestDto mockApiRequestDTO, Principal principal){
        MockApiModel model = convertToModel(mockApiRequestDTO, principal.getName());
        this.mockApiRepository.save(model);
        return convertToMockObject(model);
    }


    private MockApiModel convertToModel(MockApiRequestDto mockApiRequestDTO, String email){
        MockApiModel model = new MockApiModel();
        model.setDelay(mockApiRequestDTO.getDelay());
        model.setCreatedBy(this.userLookUpService.getUserByEmail(email));
        model.setApiVersionModel(this.apiVersionLookUpService.getById(mockApiRequestDTO.getApiVersionId()));
        model.setApiDefinitionModel(model.getApiVersionModel().getApiDefinitionModel());
        model.setProjectModel(model.getApiDefinitionModel().getProject());
        model.setHttpMethods(mockApiRequestDTO.getHttpMethod());
        return model;
    }

    private ApiDefinitionResponseDto convertToMockObject(MockApiModel model){
        ApiDefinitionResponseDto mock = new ApiDefinitionResponseDto();
        mock.setId(model.getApiDefinitionModel().getId());
        mock.setProjectId(model.getProjectModel());
        mock.setUrlPath(model.getApiDefinitionModel().getPath());
        mock.setDescription(model.getApiDefinitionModel().getDescription());
        return mock;
    }
}
