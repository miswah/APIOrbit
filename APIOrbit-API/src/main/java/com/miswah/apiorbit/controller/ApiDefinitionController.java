package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.ApiDefinitionRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import com.miswah.apiorbit.service.ApiDefinitionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/api-definitions")
public class ApiDefinitionController {

    private final ApiDefinitionService apiDefinitionService;

    @Autowired
    public ApiDefinitionController(ApiDefinitionService apiDefinitionService){
        this.apiDefinitionService = apiDefinitionService;
    }

    @GetMapping
    public ResponseEntity<List<ApiDefinitionResponseDto>> getAllDefinitions(){
        return ResponseEntity.ok(apiDefinitionService.getAllDefinitions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDefinitionResponseDto> getDefinitionById(@PathVariable UUID id){
        return ResponseEntity.ok(apiDefinitionService.getDefinitionById(id));
    }

    @PostMapping
    public ResponseEntity<ApiDefinitionResponseDto> createDefinition(@Valid @RequestBody ApiDefinitionRequestDto apiDefinitionRequestDto, Principal principal){
        return ResponseEntity.ok(apiDefinitionService.createDefinition(apiDefinitionRequestDto, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDefinitionResponseDto> updateDefinition(@Valid @RequestBody ApiDefinitionRequestDto apiDefinitionRequestDto, @PathVariable UUID id){
        return  ResponseEntity.ok(apiDefinitionService.updateDefinition(apiDefinitionRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDefinitionResponseDto> deleteDefinition(@PathVariable UUID id){
        return ResponseEntity.ok(apiDefinitionService.deleteDefinition(id));
    }
}
