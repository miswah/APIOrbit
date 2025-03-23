package com.miswah.apiorbit.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/editor")
public class EditorController {

    @GetMapping
    public String editorEndpoint() {
        return "Editor access only";
    }
}
