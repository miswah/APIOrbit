package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.request.ApiVersionRequestDto;
import com.miswah.apiorbit.service.ApiVersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/api-version")
public class ApiVersionController {
    private final ApiVersionService apiVersionService;

    @Autowired
    public ApiVersionController(ApiVersionService apiVersionService){
        this.apiVersionService = apiVersionService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ApiVersionRequestDto apiVersionRequestDto){
        String str = this.apiVersionService.create(apiVersionRequestDto);
        return ResponseEntity.ok(str);
    }
}
