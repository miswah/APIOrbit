package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.ApiRequestDTO;
import com.miswah.apiorbit.dto.response.ApiResponseDTO;
import com.miswah.apiorbit.service.ApiService;
import com.miswah.apiorbit.utils.ActivityLog;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/base")
public class ApiController {

    public final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService){
        this.apiService = apiService;
    }

    @GetMapping
    @ActivityLog(action="GET_APPROVED_API", module="API")
    public ResponseEntity<List<ApiResponseDTO>> getAllApiDetails(){
        return ResponseEntity.ok(this.apiService.getApprovedApis());
    }

    @GetMapping("/{id}")
    @ActivityLog(action="GET_API_BY_ID", module="API", target="#result?.id")
    public ResponseEntity<ApiResponseDTO> getApiDetail(@PathVariable Long id){
        return ResponseEntity.ok(this.apiService.getApi(id));
    }

    @PutMapping("/approve/{id}")
    @ActivityLog(action="APPROVE_API", module="API", target="#result?.id")
    public ResponseEntity<ApiResponseDTO> approveApi(@PathVariable Long id, Principal principal){
        return ResponseEntity.ok(this.apiService.approveApi(id, principal));
    }

    @PostMapping
    @ActivityLog(action="CREATE_API", module="API", target="#result?.id", extra = "Creating a new api with this body")
    public ResponseEntity<ApiResponseDTO> createApiDetails(@Valid @RequestBody ApiRequestDTO dto){
        return ResponseEntity.ok(this.apiService.createApi(dto));
    }

    @PutMapping("/{id}")
    @ActivityLog(action="UPDATE_API", module="API", target="#result?.id")
    public ResponseEntity<ApiResponseDTO> updateApiDetails(@Valid @RequestBody ApiRequestDTO dto, @PathVariable Long id, Principal principal){
        return ResponseEntity.ok(this.apiService.updateApi(dto, id, principal));
    }
}
