package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.DocumentationRequestDTO;
import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;
import com.miswah.apiorbit.model.DocumentationModel;
import com.miswah.apiorbit.repository.DocumentationRepository;
import com.miswah.apiorbit.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class DocumentationServiceImpl implements DocumentationService {
    private final DocumentationRepository docRepository;

    @Autowired
    public DocumentationServiceImpl(DocumentationRepository docRepository){
        this.docRepository = docRepository;
    }


    @Override
    public DocumentationResponseDTO getDocsByApiId(UUID apiId) {
        Optional<DocumentationModel> model = this.docRepository.findByApi_Id(apiId);
        return model.map(this::convertToDto).orElseGet(() -> new DocumentationResponseDTO(apiId, null, null));
    }

    @Override
    public DocumentationResponseDTO updatedDocsById(UUID id, DocumentationRequestDTO dto) {
        Optional<DocumentationModel> model = this.docRepository.findById(id);
        return null;
    }

    private DocumentationResponseDTO convertToDto(DocumentationModel model){
        return new DocumentationResponseDTO(model.getApiModel().getId(), model.getId(), model.getText());
    }
}
