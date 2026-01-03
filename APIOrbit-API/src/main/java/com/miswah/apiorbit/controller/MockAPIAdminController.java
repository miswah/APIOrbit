package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.MockApiRequestDto;
import com.miswah.apiorbit.dto.response.ApiDefinitionResponseDto;
import com.miswah.apiorbit.service.MockApiAdminService;
import com.miswah.apiorbit.utils.ActivityLog;
import com.miswah.apiorbit.utils.Loggable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/mock-admin")
public class MockAPIAdminController {

    private final MockApiAdminService mockApiAdminService;

    @Autowired
    public MockAPIAdminController(MockApiAdminService mockApiAdminService){
        this.mockApiAdminService = mockApiAdminService;
    }

    @PostMapping
    @ActivityLog(action="CREATE_MOCK", module="MOCK")
    @Loggable
    public ResponseEntity<ApiDefinitionResponseDto> createMock(@Valid @RequestBody MockApiRequestDto mockApiRequestDTO, Principal principal) {
        ApiDefinitionResponseDto mock = this.mockApiAdminService.createMock(mockApiRequestDTO, principal);
        return new ResponseEntity<>(mock, HttpStatus.CREATED);
    }
}
