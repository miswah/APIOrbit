package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.model.ApiDefinitionModel;
import com.miswah.apiorbit.repository.ApiDefinitionRepository;
import com.miswah.apiorbit.service.ApiDefinitionLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApiDefinitionLookUpServiceImpl implements ApiDefinitionLookUpService {
    private final ApiDefinitionRepository apiDefinitionRepository;

    @Autowired
    public ApiDefinitionLookUpServiceImpl(ApiDefinitionRepository apiDefinitionRepository){
        this.apiDefinitionRepository = apiDefinitionRepository;
    }

    @Override
    public ApiDefinitionModel findById(UUID id) {
        return this.apiDefinitionRepository.findById(id).orElseThrow();
    }
}
