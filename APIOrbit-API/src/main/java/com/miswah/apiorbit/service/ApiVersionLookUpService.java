package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ApiVersionModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ApiVersionLookUpService {

    ApiVersionModel getById(UUID id);
}
