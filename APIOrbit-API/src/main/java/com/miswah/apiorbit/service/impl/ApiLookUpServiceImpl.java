package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.model.DocumentationModel;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.service.ApiLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApiLookUpServiceImpl implements ApiLookUpService {
   private final ApiRepository apiRepository;

   @Autowired
   public ApiLookUpServiceImpl(ApiRepository apiRepository){
       this.apiRepository = apiRepository;
   }

    @Override
    public ApiModel findById(UUID id) {
        return this.apiRepository.findById(id).orElseThrow();
    }
}
