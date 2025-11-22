package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;
import com.miswah.apiorbit.model.DocumentationModel;
import com.miswah.apiorbit.repository.DocumentationRepository;
import com.miswah.apiorbit.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentationServiceImpl implements DocumentationService {
    private final DocumentationRepository docRepository;

    @Autowired
    public DocumentationServiceImpl(DocumentationRepository docRepository){
        this.docRepository = docRepository;
    }


    @Override
    public DocumentationResponseDTO getDocsById(UUID id) {
        Optional<DocumentationModel> model = this.docRepository.findById(id);
        return model.map(this::convertToDto).orElseGet(() -> new DocumentationResponseDTO(id, null, null));
    }

    private DocumentationResponseDTO convertToDto(DocumentationModel model){
        return new DocumentationResponseDTO(model.getApiModel().getId(), model.getId(), model.getText());
    }
}
