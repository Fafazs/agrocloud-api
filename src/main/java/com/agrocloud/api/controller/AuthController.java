package com.agrocloud.api.controller;

import com.agrocloud.api.dto.*;
import com.agrocloud.api.model.User;
import com.agrocloud.api.security.JwtUtil;
import com.agrocloud.api.service.AgroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AgroService agroService;
    private final JwtUtil jwtUtil;

    public AuthController(AgroService agroService, JwtUtil jwtUtil) {
        this.agroService = agroService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = agroService.registerUser(request);
        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token, user.getId(), user.getName(), user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return agroService.loginUser(request.getEmail(), request.getPassword())
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getId(), user.getEmail());
                    return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getName(), user.getEmail()));
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/anonymous")
public ResponseEntity<AuthResponse> anonymous() {

    User anon =
        agroService.createAnonymousUser();

    String token =
        jwtUtil.generateToken(
            anon.getId(),
            anon.getEmail()
        );

    return ResponseEntity.ok(
        new AuthResponse(
            token,
            anon.getId(),
            anon.getName(),
            anon.getEmail()
        )
    );
}
}