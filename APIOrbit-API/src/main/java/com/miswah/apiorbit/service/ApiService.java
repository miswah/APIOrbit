package com.miswah.apiorbit.service;


import com.miswah.apiorbit.dto.request.ApiRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public interface ApiService {
    ApiResponseDTO createApi(ApiRequestDTO data);

    List<ApiResponseDTO> getApprovedApis();

    ApiResponseDTO getApi(UUID id);

    ApiResponseDTO approveApi(UUID id, Principal principal);

    ApiResponseDTO updateApi(ApiRequestDTO data, UUID id, Principal principal);

    List<ApiResponseDTO> getAllApi();

    ApiResponseDTO disableApi(UUID id, Principal principal);
}
