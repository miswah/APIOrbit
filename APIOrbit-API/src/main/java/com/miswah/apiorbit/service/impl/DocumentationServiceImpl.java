package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.request.DocumentationRequestDTO;
import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;
import com.miswah.apiorbit.model.DocumentationModel;
import com.miswah.apiorbit.repository.DocumentationRepository;
import com.miswah.apiorbit.service.ApiLookUpService;
import com.miswah.apiorbit.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class DocumentationServiceImpl implements DocumentationService {
    private final DocumentationRepository docRepository;
    private final ApiLookUpService apiLookUpService;

    @Autowired
    public DocumentationServiceImpl(DocumentationRepository docRepository, ApiLookUpService apiLookUpService){
        this.docRepository = docRepository;
        this.apiLookUpService = apiLookUpService;
    }


    @Override
    public DocumentationResponseDTO getDocsByApiId(UUID apiId) {
        Optional<DocumentationModel> model = this.docRepository.findByApiId(apiId);
        return model.map(this::convertToDto).orElseGet(() -> new DocumentationResponseDTO(apiId, null, null));
    }

    @Override
    public DocumentationResponseDTO updatedDocsById(UUID apiId, DocumentationRequestDTO dto) {
        Optional<DocumentationModel> model = this.docRepository.findByApiId(apiId);

        if(model.isEmpty()){
            DocumentationModel newModel = new DocumentationModel();
            newModel.setApi(this.apiLookUpService.findById(dto.apiId()));
            newModel.setText(dto.text());
            this.docRepository.save(newModel);
            return this.convertToDto(newModel);
        }

        model.get().setText(dto.text());
        this.docRepository.save(model.get());
        return this.convertToDto(model.get());
    }

    private DocumentationResponseDTO convertToDto(DocumentationModel model){
        return new DocumentationResponseDTO(model.getApi().getId(), model.getId(), model.getText());
    }

    private DocumentationModel convertToModel(DocumentationRequestDTO dto){
        return new DocumentationModel(dto.id(), this.apiLookUpService.findById(dto.apiId()), dto.text());
    }
}
