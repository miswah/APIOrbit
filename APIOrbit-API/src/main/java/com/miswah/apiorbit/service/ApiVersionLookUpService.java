package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ApiVersionModel;
import org.springframework.stereotype.Service;

@Service
public interface ApiVersionLookUpService {

    ApiVersionModel getById(Long id);
}
