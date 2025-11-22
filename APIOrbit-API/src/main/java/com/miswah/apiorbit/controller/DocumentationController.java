package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.response.DocumentationResponseDTO;
import com.miswah.apiorbit.service.DocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(this.documentationService.getDocsById(id));
    }
}
