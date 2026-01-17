package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.response.ApiByMethodCountResponseDTO;
import com.miswah.apiorbit.dto.response.SystemKPIResponseDTO;
import com.miswah.apiorbit.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/overview")
    public ResponseEntity<SystemKPIResponseDTO> getSystemKPI(){
        return ResponseEntity.ok(this.dashboardService.getSystemKPI());
    }

    @GetMapping("/by-method")
    public ResponseEntity<List<ApiByMethodCountResponseDTO>> getApisCountsByMethod(){
        return ResponseEntity.ok(this.dashboardService.getApiCountByMethod());
    }

    @GetMapping("/by-status")
    public ResponseEntity<>
}
