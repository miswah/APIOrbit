package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.request.DocumentationRequestDTO;
import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;

import java.util.UUID;

public interface DocumentationService {

    DocumentationResponseDTO getDocsByApiId(UUID apiId);
    DocumentationResponseDTO updatedDocsById(UUID apiId, DocumentationRequestDTO dto);
}
