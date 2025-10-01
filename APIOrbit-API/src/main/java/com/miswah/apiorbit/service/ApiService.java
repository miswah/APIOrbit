package com.miswah.apiorbit.service;


import com.miswah.apiorbit.dto.request.ApiRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface ApiService {
    ApiResponseDTO createApi(ApiRequestDTO data);

    List<ApiResponseDTO> getApprovedApis();

    ApiResponseDTO getApi(Long id);

    ApiResponseDTO approveApi(Long id, Principal principal);

    ApiResponseDTO updateApi(ApiRequestDTO data, Long id, Principal principal);
}
