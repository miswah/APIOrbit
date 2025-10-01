package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.ApiRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import com.miswah.apiorbit.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/base")
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService){
        this.apiService = apiService;
    }

    @GetMapping
    public ResponseEntity<List<ApiResponseDTO>> getAllApiDetails(){
        return ResponseEntity.ok(this.apiService.getApprovedApis());
    }

    @GetMapping("/{id}")
    private ResponseEntity<ApiResponseDTO> getApiDetail(@PathVariable Long id){
        return ResponseEntity.ok(this.apiService.getApi(id));
    }

    @PutMapping("/approve/{id}")
    private ResponseEntity<ApiResponseDTO> approveApi(@PathVariable Long id, Principal principal){
        return ResponseEntity.ok(this.apiService.approveApi(id, principal));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> createApiDetails(@Valid @RequestBody ApiRequestDTO dto){
        return ResponseEntity.ok(this.apiService.createApi(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ApiResponseDTO> updateApiDetails(@Valid @RequestBody ApiRequestDTO dto, @PathVariable Long id, Principal principal){
        return ResponseEntity.ok(this.apiService.updateApi(dto, id, principal));
    }
}
