package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.ApiRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import com.miswah.apiorbit.enums.ApiStatus;
import com.miswah.apiorbit.exception.ResourceNotFoundException;
import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    private final ApiRepository apiRepository;

    @Autowired
    public ApiServiceImpl(ApiRepository apiRepository){
        this.apiRepository = apiRepository;
    }

    @Override
    public ApiResponseDTO createApi(ApiRequestDTO dto) {
        ApiModel model = this.convertToModel(dto);
        this.apiRepository.save(model);
        logger.info("New API created {}", model.toString());
        return this.convertToDto(model);
    }

    @Override
    public List<ApiResponseDTO> getApprovedApis() {
        List<ApiModel> models = this.apiRepository.findByStatus(ApiStatus.ACTIVE);
        logger.info("Approved api fetched");
        return this.convertToDtoList(models);
    }

    @Override
    public ApiResponseDTO getApi(Long id) {
        Optional<ApiModel> model = this.apiRepository.findById(id);

        if(model.isEmpty()){
            logger.error("No api found for getApi() called");
            throw new ResourceNotFoundException("No Api found with that id");
        }
        return this.convertToDto(model.get());
    }

    @Override
    public ApiResponseDTO approveApi(Long id, Principal principal) {
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
    public ApiResponseDTO updateApi(ApiRequestDTO dto, Long id, Principal principal) {
        Optional<ApiModel> model = this.apiRepository.findById(id);

        if(model.isEmpty()){
            throw new ResourceNotFoundException("No Api found with that id");
        }

        ApiModel apiModel = model.get();

        if(dto.getName() != null) apiModel.setName(dto.getName());
        if(dto.getDescription() != null) apiModel.setDescription(dto.getDescription());
        if(dto.getCategory() != null) apiModel.setCategory(dto.getCategory());
        if(dto.getTags() != null) apiModel.setTags(dto.getTags());
        if(dto.getUrlBase() != null) apiModel.setBaseUrl(dto.getUrlBase());
        if(dto.getVersion() != 0) apiModel.setVersion(dto.getVersion());
        if(dto.getAuthType() != null) apiModel.setAuthTypeNames(dto.getAuthType());
        if(dto.getDocumentationUrl() != null) apiModel.setDocumentationUrl(dto.getDocumentationUrl());
        if(dto.getMockUrl() != null) apiModel.setMockUrl(dto.getMockUrl());

        this.apiRepository.save(apiModel);

        return this.convertToDto(apiModel);
    }

    private ApiModel convertToModel(ApiRequestDTO dto){
        ApiModel model = new ApiModel();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setCategory(dto.getCategory());
        model.setTags(dto.getTags());
        model.setBaseUrl(dto.getUrlBase());
        model.setVersion(dto.getVersion());
        model.setStatus(ApiStatus.PENDING);
        model.setAuthTypeNames(dto.getAuthType());
        model.setDocumentationUrl(dto.getDocumentationUrl());
        model.setMockUrl(dto.getMockUrl());
        return model;
    }

    private ApiResponseDTO convertToDto(ApiModel model){
        ApiResponseDTO dto = new ApiResponseDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        dto.setCategory(model.getCategory());
        dto.setTags(model.getTags());
        dto.setUrlBase(model.getBaseUrl());
        dto.setVersion(model.getVersion());
        dto.setStatus(model.getStatus());
        dto.setAuthType(model.getAuthTypeNames());
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
