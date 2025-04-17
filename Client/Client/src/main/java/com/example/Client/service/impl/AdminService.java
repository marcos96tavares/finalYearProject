package com.example.Client.service.impl;

import com.example.Client.dto.AdminDto;
import com.example.Client.entity.Admin;
import com.example.Client.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

private final AdminRepository adminRepository;
private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public AdminDto CreateAdmin (AdminDto admin) {

        Admin adminEntity = new Admin();
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminEntity.setRole("ROLE_ADMIN");

         adminRepository.save(adminEntity);
         return admin;
    }

}
