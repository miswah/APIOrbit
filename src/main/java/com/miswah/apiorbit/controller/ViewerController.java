package com.miswah.apiorbit.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/viewer")
public class ViewerController {
    @GetMapping
    public String managerEndpoint() {
        return "Manager access only";
    }
}
