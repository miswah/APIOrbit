package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ApiDefinitionModel;
import org.springframework.stereotype.Service;

@Service
public interface ApiDefinitionLookUpService {
    ApiDefinitionModel findById(Long id);
}
