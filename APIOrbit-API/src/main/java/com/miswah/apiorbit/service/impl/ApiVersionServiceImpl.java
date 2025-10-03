package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.ApiVersionRequestDto;
import com.miswah.apiorbit.dto.response.ApiVersionResponseDto;
import com.miswah.apiorbit.enums.ResourceStatus;
import com.miswah.apiorbit.exception.ResourceNotFoundException;

import com.miswah.apiorbit.model.ApiVersionModel;
import com.miswah.apiorbit.repository.ApiVersionRespository;
import com.miswah.apiorbit.service.ApiDefinitionLookUpService;
import com.miswah.apiorbit.service.ApiVersionService;
import com.miswah.apiorbit.utils.CustomLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApiVersionServiceImpl implements ApiVersionService {
    private final ApiVersionRespository apiVersionRespository;
    private final ApiDefinitionLookUpService apiDefinitionLookUpService;

    @Autowired
    public ApiVersionServiceImpl(ApiVersionRespository apiVersionRespository, ApiDefinitionLookUpService apiDefinitionLookUpService){
        this.apiVersionRespository = apiVersionRespository;
        this.apiDefinitionLookUpService = apiDefinitionLookUpService;
    }

    @Override
    public String create(ApiVersionRequestDto apiVersionRequestDto) {
        ApiVersionModel model = convertToModel(apiVersionRequestDto);
        model.setStatus(ResourceStatus.PENDING);
        this.apiVersionRespository.save(model);

        return "ok";
    }

    @Override
    public List<ApiVersionResponseDto> getByApiVersion(UUID apiDefinitionId) {
        Optional<List<ApiVersionModel>> model = this.apiVersionRespository.findByApiDefinitionModelAndStatus(this.apiDefinitionLookUpService.findById(apiDefinitionId), ResourceStatus.ACTIVE);
        if(model.isEmpty()){
            CustomLogger.logError(ApiVersionServiceImpl.class, "Fetch API version by definition id : "+apiDefinitionId, new ResourceNotFoundException("No Api version found with that definition id"));
            throw new ResourceNotFoundException("No Api version found with that definition id");
        }
        return this.convertToDtoList(model.get());
    }

    @Override
    public ApiVersionResponseDto updateById(UUID id, ApiVersionRequestDto dto) {
        Optional<ApiVersionModel> model = this.apiVersionRespository.findById(id);
        if(model.isEmpty()){
            CustomLogger.logError(ApiVersionServiceImpl.class, "Fetch API version by id : "+id, new ResourceNotFoundException("No Api version found with that id"));
            throw new ResourceNotFoundException("No Api version found with that id");
        }

        model.get().setSchemaRequest(dto.getSchemaRequest());
        model.get().setSchemaResponse(dto.getSchemaResponse());
        model.get().setApiDependencyModel(dto.getApiDependencyModel());

        this.apiVersionRespository.save(model.get());

        return this.convertToDto(model.get());
    }

    @Override
    public ApiVersionResponseDto deleteById(UUID id) {
        Optional<ApiVersionModel> model = this.apiVersionRespository.findById(id);

        if(model.isEmpty()){
            CustomLogger.logError(ApiVersionServiceImpl.class, "Fetch API version by id : "+id, new ResourceNotFoundException("No Api version found with that id"));
            throw new ResourceNotFoundException("No Api version found with that id");
        }

        //TODO : purge for deleted resources
        model.get().setStatus(ResourceStatus.DELETE);
        this.apiVersionRespository.save(model.get());

        return this.convertToDto(model.get());
    }

    @Override
    public ApiVersionResponseDto approveById(UUID id) {
        Optional<ApiVersionModel> model = this.apiVersionRespository.findById(id);

        if(model.isEmpty()){
            CustomLogger.logError(ApiVersionServiceImpl.class, "Fetch API version by id : "+id, new ResourceNotFoundException("No Api version found with that id"));
            throw new ResourceNotFoundException("No Api version found with that id");
        }

        model.get().setStatus(ResourceStatus.ACTIVE);
        this.apiVersionRespository.save(model.get());
        return this.convertToDto(model.get());
    }

    private ApiVersionModel convertToModel(ApiVersionRequestDto dto){
        ApiVersionModel model = new ApiVersionModel();
        model.setVersion(dto.getVersion());
        model.setStatus(dto.getResourceStatus());
        model.setApiDefinitionModel(this.apiDefinitionLookUpService.findById(dto.getApiDefinitionId()));
        model.setSchemaRequest(dto.getSchemaRequest());
        model.setSchemaResponse(dto.getSchemaResponse());
        return model;
    }

    private ApiVersionResponseDto convertToDto(ApiVersionModel model){
        ApiVersionResponseDto dto = new ApiVersionResponseDto();
        dto.setId(model.getId());
        dto.setApiDefinitionId(model.getApiDefinitionModel().getId());
        dto.setApiDependencyModel(model.getApiDependencyModel());
        dto.setResourceStatus(model.getStatus());
        dto.setSchemaRequest(model.getSchemaRequest());
        dto.setSchemaResponse(model.getSchemaResponse());
        dto.setVersion(model.getVersion());
        return dto;
    }

    private List<ApiVersionResponseDto> convertToDtoList(List<ApiVersionModel> models){
        return models.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
