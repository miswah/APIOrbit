package com.miswah.apiorbit.controller;

import com.miswah.apiorbit.dto.request.AuthenticationRequest;
import com.miswah.apiorbit.dto.request.RegisterRequest;
import com.miswah.apiorbit.dto.response.AuthenticationResponse;
import com.miswah.apiorbit.dto.response.UserResponseDTO;
import com.miswah.apiorbit.enums.Roles;
import com.miswah.apiorbit.security.AuthenticationService;
import com.miswah.apiorbit.utils.Loggable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/editor")
    public ResponseEntity<String> registerEditor(
            @RequestBody RegisterRequest request
    ) {
        //TODO: add user details in response
        return ResponseEntity.ok(authenticationService.register(request, Roles.EDITOR));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        //TODO: add user details in response
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }


    @PostMapping("/register/viewer")
    public ResponseEntity<String> registerViewer(
            @RequestBody RegisterRequest request
    ) {
        //TODO: add user details in response
        return ResponseEntity.ok(authenticationService.register(request, Roles.VIEWER));
    }

    @PostMapping("/login")
    @Loggable
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
