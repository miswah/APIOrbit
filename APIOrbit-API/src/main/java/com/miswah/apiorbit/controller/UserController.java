package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.response.UserResponseDTO;
import com.miswah.apiorbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<UserResponseDTO> approveUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.approve(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> disableUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.disable(id));
    }
}
