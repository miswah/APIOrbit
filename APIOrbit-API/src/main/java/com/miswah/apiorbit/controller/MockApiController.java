package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.response.MockApiResponseDTO;
import com.miswah.apiorbit.service.MockApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/mock")
@RestController
public class MockApiController {

    private final MockApiService mockApiService;

    @Autowired
    public MockApiController(MockApiService mockApiService){
        this.mockApiService = mockApiService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MockApiResponseDTO> getMockByApiId(@PathVariable UUID id){
        return ResponseEntity.ok(this.mockApiService.getMockByApiId(id));
    }
}
