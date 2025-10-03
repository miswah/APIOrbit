package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ApiDefinitionModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ApiDefinitionLookUpService {
    ApiDefinitionModel findById(UUID id);
}
