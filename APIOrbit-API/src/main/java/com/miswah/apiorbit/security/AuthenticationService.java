package com.miswah.apiorbit.security;

import com.miswah.apiorbit.dto.request.AuthenticationRequest;
import com.miswah.apiorbit.dto.request.RegisterRequest;
import com.miswah.apiorbit.dto.response.AuthenticationResponse;
import com.miswah.apiorbit.enums.Roles;
import com.miswah.apiorbit.enums.UserStatus;
import com.miswah.apiorbit.model.UserModel;
import com.miswah.apiorbit.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new UserModel();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Roles.VIEWER);
        // TODO: User approval flow
        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse res = new AuthenticationResponse();
        res.setToken(jwtToken);
        res.setRole(user.getRole());
        res.setName(user.getName());
        return res;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse res = new AuthenticationResponse();
        res.setToken(jwtToken);
        res.setRole(user.getRole());
        res.setName(user.getName());
        return res;
    }
}
