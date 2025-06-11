package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.ApiVersionRequestDto;
import com.miswah.apiorbit.model.ApiVersionModel;
import com.miswah.apiorbit.repository.ApiVersionRespository;
import com.miswah.apiorbit.service.ApiDefinitionLookUpService;
import com.miswah.apiorbit.service.ApiVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        this.apiVersionRespository.save(model);

        return "ok";
    }

    private ApiVersionModel convertToModel(ApiVersionRequestDto dto){
        ApiVersionModel model = new ApiVersionModel();
        model.setVersion(dto.getVersion());
        model.setStatus(dto.getApiStatus());
        model.setApiDefinitionModel(this.apiDefinitionLookUpService.findById(dto.getApiDefinitionId()));
        model.setSchemaRequest(dto.getSchemaRequest());
        model.setSchemaResponse(dto.getSchemaResponse());
        return model;
    }
}
