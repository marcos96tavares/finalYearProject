package com.example.Client.service.impl;


import com.example.Client.Config.JwtTokenProvider;
import com.example.Client.dto.LoginDto;
import com.example.Client.entity.Admin;
import com.example.Client.entity.User;
import com.example.Client.exception.EmailAlreadyExistsException;
import com.example.Client.exception.ResourceNotFoundException;
import com.example.Client.repository.AdminRepository;
import com.example.Client.repository.UserRepository;
import com.example.Client.service.AuthSerive;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutServiceImp implements AuthSerive {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AutServiceImp(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, AdminRepository adminRepository, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String userLogin(LoginDto user) {

        String email = user.getEmail();
        User userExists = userRepository.findByEmail(email).orElseThrow(()->new EmailAlreadyExistsException("wrong email"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String adminLogin(LoginDto admin) {

        String email = admin.getEmail();
        Admin admin1 = adminRepository.findByEmail(email).orElseThrow(()-> new EmailAlreadyExistsException("Wrong  login"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }
}
