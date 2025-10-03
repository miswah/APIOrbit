package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.ApiRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.exception.ResourceNotFoundException;
import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.service.ApiService;
import com.miswah.apiorbit.service.UserLookUpService;
import com.miswah.apiorbit.utils.CustomLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    private final ApiRepository apiRepository;
    private final UserLookUpService userLookUpService;


    @Autowired
    public ApiServiceImpl(ApiRepository apiRepository, UserLookUpService userLookUpService){
        this.apiRepository = apiRepository;
        this.userLookUpService = userLookUpService;
    }

    @Override
    public ApiResponseDTO createApi(ApiRequestDTO dto) {
        ApiModel model = this.convertToModel(dto);
        this.apiRepository.save(model);
        CustomLogger.logInfo(ApiServiceImpl.class, "Create New api");
        return this.convertToDto(model);
    }

    @Override
    public List<ApiResponseDTO> getApprovedApis() {
        List<ApiModel> models = this.apiRepository.findByStatus(ApiStatus.ACTIVE);
        CustomLogger.logInfo(ApiServiceImpl.class, "Fetch Approved API");
        return this.convertToDtoList(models);
    }

    @Override
    public ApiResponseDTO getApi(UUID id) {
        Optional<ApiModel> model = this.apiRepository.findById(id);

        if(model.isEmpty()){
            CustomLogger.logError(ApiServiceImpl.class, "Fetch API by id : "+id, new ResourceNotFoundException("No Api found with that id"));
            throw new ResourceNotFoundException("No Api found with that id");
        }
        return this.convertToDto(model.get());
    }

    @Override
    public ApiResponseDTO approveApi(UUID id, Principal principal) {
        Optional<ApiModel> model = this.apiRepository.findById(id);

        if(model.isEmpty()){
            throw new ResourceNotFoundException("No Api found with that id");
        }

        model.get().setStatus(ApiStatus.ACTIVE);
        model.get().setApprovedBy(principal.getName());

        this.apiRepository.save(model.get());

        return this.convertToDto(model.get());
    }

    @Override
    public ApiResponseDTO updateApi(ApiRequestDTO dto, UUID id, Principal principal) {
        Optional<ApiModel> model = this.apiRepository.findById(id);

        if(model.isEmpty()){
            throw new ResourceNotFoundException("No Api found with that id");
        }

        ApiModel apiModel = model.get();
        if(dto.getCategory() != null) apiModel.setCategory(dto.getCategory());
        if(dto.getDocumentationUrl() != null) apiModel.setDocumentationUrl(dto.getDocumentationUrl());
        if(dto.getMockUrl() != null) apiModel.setMockUrl(dto.getMockUrl());

        this.apiRepository.save(apiModel);

        return this.convertToDto(apiModel);
    }

    private ApiModel convertToModel(ApiRequestDTO dto){
        ApiModel model = new ApiModel();
        model.setCategory(dto.getCategory());
        model.setStatus(ApiStatus.PENDING);
        model.setDocumentationUrl(dto.getDocumentationUrl());
        model.setMockUrl(dto.getMockUrl());
        return model;
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
        return dto;
    }

    private List<ApiResponseDTO> convertToDtoList(List<ApiModel> models){
        return models.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
