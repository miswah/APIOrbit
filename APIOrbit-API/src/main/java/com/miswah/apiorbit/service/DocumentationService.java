package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;

import java.util.UUID;

public interface DocumentationService {

    DocumentationResponseDTO getDocsById(UUID id);
}
