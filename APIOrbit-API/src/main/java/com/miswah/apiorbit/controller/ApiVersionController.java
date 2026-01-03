package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.request.ApiVersionRequestDto;
import com.miswah.apiorbit.dto.response.ApiVersionResponseDto;
import com.miswah.apiorbit.service.ApiVersionService;

import com.miswah.apiorbit.utils.ActivityLog;
import com.miswah.apiorbit.utils.Loggable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/api-version")
public class ApiVersionController {
    private final ApiVersionService apiVersionService;

    @Autowired
    public ApiVersionController(ApiVersionService apiVersionService){
        this.apiVersionService = apiVersionService;
    }

    @GetMapping("/{apiDefinitionId}")
    @ActivityLog(action="GET_ALL_API_VERSION", module="API_VERSION", target="#result?.id")
    @Loggable
    public ResponseEntity<List<ApiVersionResponseDto>> getAllApiVersion(@PathVariable UUID apiDefinitionId){
        return ResponseEntity.ok(this.apiVersionService.getByApiVersion(apiDefinitionId));
    }

    @PostMapping
    @ActivityLog(action="CREATE_API_VERSION", module="API_VERSION")
    @Loggable
    public ResponseEntity<String> create(@Validated @RequestBody ApiVersionRequestDto dto){
        String str = this.apiVersionService.create(dto);
        return ResponseEntity.ok(str);
    }

    @PutMapping("/{id}")
    @ActivityLog(action="UPDATE_API_VERSION", module="API_VERSION", target="#result?.id")
    @Loggable
    public ResponseEntity<ApiVersionResponseDto> updateById(@Valid @RequestBody ApiVersionRequestDto dto, @PathVariable UUID id){
        return ResponseEntity.ok(this.apiVersionService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    @ActivityLog(action="DELETE_API_VERSION", module="API_VERSION", target="#result?.id")
    @Loggable
    public ResponseEntity<ApiVersionResponseDto> deleteById(@PathVariable UUID id){
        return ResponseEntity.ok(this.apiVersionService.deleteById(id));
    }

    @PutMapping("/approve/{id}")
    @ActivityLog(action="APPROVE_API_VERSION_BY_ID", module="API_VERSION", target="#result?.id")
    @Loggable
    public ResponseEntity<ApiVersionResponseDto> approveById(@PathVariable UUID id){
        return ResponseEntity.ok(this.apiVersionService.approveById(id));
    }
}
