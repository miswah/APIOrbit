package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.ApiDefinitionRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import com.miswah.apiorbit.model.ApiDefinitionModel;
import com.miswah.apiorbit.repository.ApiDefinitionRepository;
import com.miswah.apiorbit.service.ApiDefinitionService;
import com.miswah.apiorbit.service.ProjectLookUpService;
import com.miswah.apiorbit.service.UserLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApiDefinitionServiceImpl implements ApiDefinitionService {

    private final ApiDefinitionRepository apiDefinitionRepository;
    private final ProjectLookUpService projectLookUpService;
    private final UserLookUpService userLookUpService;

    @Autowired
    public ApiDefinitionServiceImpl(ApiDefinitionRepository apiDefinitionRepository, ProjectLookUpService projectLookUpService, UserLookUpService userLookUpService){
        this.apiDefinitionRepository = apiDefinitionRepository;
        this.projectLookUpService = projectLookUpService;
        this.userLookUpService = userLookUpService;
    }

    @Override
    public List<ApiDefinitionResponseDto> getAllDefinitions() {
        List<ApiDefinitionModel> apiDefinitionModelList = this.apiDefinitionRepository.findAll();

        return this.convertToDtoList(apiDefinitionModelList);
    }

    @Override
    public ApiDefinitionResponseDto getDefinitionById(Long id) {
        return this.convertToDto(this.apiDefinitionRepository.findById(id).orElseThrow());
    }

    @Override
    public ApiDefinitionResponseDto createDefinition(ApiDefinitionRequestDto apiDefinitionRequestDto, String email) {
        ApiDefinitionModel apiDefinitionModel = convertToModel(apiDefinitionRequestDto, email);
        this.apiDefinitionRepository.save(apiDefinitionModel);
        return this.convertToDto(apiDefinitionModel);
    }

    @Override
    public ApiDefinitionResponseDto updateDefinition(ApiDefinitionRequestDto apiDefinitionRequestDto, Long id) {
        Optional<ApiDefinitionModel> apiDefinitionModel = this.apiDefinitionRepository.findAllById(id);
        if(apiDefinitionModel.isEmpty()){
            throw new RuntimeException("No Api Definition found with that id");
        }

        apiDefinitionModel.get().setPath(apiDefinitionRequestDto.getUrlPath());
        apiDefinitionModel.get().setHttpMethod(apiDefinitionRequestDto.getHttpMethods());
        apiDefinitionModel.get().setAuthType(apiDefinitionRequestDto.getAuthTypeNames());
        apiDefinitionModel.get().setDescription(apiDefinitionRequestDto.getDescription());
        apiDefinitionModel.get().setProject(this.projectLookUpService.getProjectById(apiDefinitionRequestDto.getProjectId()));

        this.apiDefinitionRepository.save(apiDefinitionModel.get());

        return this.convertToDto(apiDefinitionModel.get());
    }

    @Override
    public ApiDefinitionResponseDto deleteDefinition(Long id) {
        Optional<ApiDefinitionModel> apiDefinitionModel = this.apiDefinitionRepository.findAllById(id);
        if(apiDefinitionModel.isEmpty()){
            throw new RuntimeException("No Api Definition found with that id");
        }

        this.apiDefinitionRepository.delete(apiDefinitionModel.get());
        return this.convertToDto(apiDefinitionModel.get());
    }

    private ApiDefinitionResponseDto convertToDto(ApiDefinitionModel apiDefinitionModel){
        ApiDefinitionResponseDto apiDefinitionResponseDto = new ApiDefinitionResponseDto();
        apiDefinitionResponseDto.setId(apiDefinitionModel.getId());
        apiDefinitionResponseDto.setProjectId(apiDefinitionModel.getProject().getId());
        apiDefinitionResponseDto.setDescription(apiDefinitionModel.getDescription());
        apiDefinitionResponseDto.setUrlPath(apiDefinitionModel.getPath());

        return apiDefinitionResponseDto;
    }

    private List<ApiDefinitionResponseDto> convertToDtoList(List<ApiDefinitionModel> apiDefinitionModels){
        return apiDefinitionModels.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ApiDefinitionModel convertToModel(ApiDefinitionRequestDto apiDefinitionRequestDto, String email){
        ApiDefinitionModel apiDefinitionModel = new ApiDefinitionModel();
        apiDefinitionModel.setPath(apiDefinitionRequestDto.getUrlPath());
        apiDefinitionModel.setDescription(apiDefinitionRequestDto.getDescription());
        apiDefinitionModel.setAuthType(apiDefinitionRequestDto.getAuthTypeNames());
        apiDefinitionModel.setHttpMethod(apiDefinitionRequestDto.getHttpMethods());

        apiDefinitionModel.setProject(this.projectLookUpService.getProjectById(apiDefinitionRequestDto.getProjectId()));

        apiDefinitionModel.setCreatedBy(this.userLookUpService.getUserByEmail(email));

        return apiDefinitionModel;
    }
}
