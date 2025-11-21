package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.response.UserResponseDTO;
import com.miswah.apiorbit.service.UserService;
import com.miswah.apiorbit.utils.ActivityLog;
import com.miswah.apiorbit.utils.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    @ActivityLog(action="GET_ALL_USERS", module="USERS")
    @Loggable
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Loggable
    @ActivityLog(action="GET_USER_BY_ID", module="USERS")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @GetMapping("/approve/{id}")
    @Loggable
    @ActivityLog(action="APPROVE_USER_BY_ID", module="USERS")
    public ResponseEntity<UserResponseDTO> approveUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.approve(id));
    }

    @DeleteMapping("/{id}")
    @Loggable
    @ActivityLog(action="DISABLE_USER_BY_ID", module="USERS")
    public ResponseEntity<UserResponseDTO> disableUser(@PathVariable UUID id){
        return ResponseEntity.ok(this.userService.disable(id));
    }
}
