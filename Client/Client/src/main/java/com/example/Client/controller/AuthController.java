package com.example.Client.controller;


import com.example.Client.dto.JwtAuthResponse;
import com.example.Client.dto.LoginDto;
import com.example.Client.service.AuthSerive;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/login")
public class AuthController {


    private final AuthSerive authSerive;

    public AuthController(AuthSerive authSerive) {
        this.authSerive = authSerive;
    }


    @PostMapping("/user")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto) {
        String token = authSerive.userLogin(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);

    }

    @PostMapping("/admin")
    public ResponseEntity<JwtAuthResponse> loginAdmin(@RequestBody LoginDto loginDto) {
        String token = authSerive.adminLogin(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);


    }




}
