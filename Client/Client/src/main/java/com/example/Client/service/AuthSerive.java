package com.example.Client.service;

import com.example.Client.dto.LoginDto;

public interface AuthSerive {


    String userLogin(LoginDto user);
    String adminLogin(LoginDto admin);
}
