package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.request.DocumentationRequestDTO;
import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;
import com.miswah.apiorbit.service.DocumentationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/docs")
public class DocumentationController {

    private final DocumentationService documentationService;

    @Autowired
    public DocumentationController(DocumentationService documentationService){
        this.documentationService = documentationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentationResponseDTO> getDocById(@PathVariable UUID id){
        return ResponseEntity.ok(this.documentationService.getDocsByApiId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentationResponseDTO> updatedDocById(@PathVariable UUID id, @Valid @RequestBody DocumentationRequestDTO dto){
        return ResponseEntity.ok(this.documentationService.updatedDocsById(id, dto));
    }
}
