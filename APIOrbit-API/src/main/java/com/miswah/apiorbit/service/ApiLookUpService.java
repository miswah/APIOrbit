package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.ApiModel;

import java.util.UUID;

public interface ApiLookUpService {
    ApiModel findById(UUID id);
}
