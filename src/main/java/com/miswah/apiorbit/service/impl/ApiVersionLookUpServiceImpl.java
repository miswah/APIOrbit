package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.model.ApiVersionModel;
import com.miswah.apiorbit.repository.ApiVersionRespository;
import com.miswah.apiorbit.service.ApiVersionLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiVersionLookUpServiceImpl implements ApiVersionLookUpService {
    private final ApiVersionRespository apiVersionRespository;

    @Autowired
    public ApiVersionLookUpServiceImpl(ApiVersionRespository apiVersionRespository){
        this.apiVersionRespository = apiVersionRespository;
    }

    @Override
    public ApiVersionModel getById(Long id) {
        return this.apiVersionRespository.findById(id).orElseThrow();
    }
}
