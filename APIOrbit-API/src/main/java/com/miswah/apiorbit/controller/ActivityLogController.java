package com.miswah.apiorbit.controller;


import com.miswah.apiorbit.dto.response.ActivityLogResponseDTO;
import com.miswah.apiorbit.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityLogController {

    private final ActivityLogService service;

    @Autowired
    public ActivityLogController(ActivityLogService service){
        this.service = service;
    }


    @GetMapping()
    public ResponseEntity<List<ActivityLogResponseDTO>> getActivityLogs(){
        return new ResponseEntity<>(this.service.getLogs(), HttpStatus.OK);
    }
}
