package com.example.Client.service.impl;

import com.example.Client.dto.AdminDto;
import com.example.Client.entity.Admin;
import com.example.Client.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }



    public AdminDto CreateAdmin (AdminDto admin) {

        Admin adminEntity = new Admin();
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setPassword(admin.getPassword());

         adminRepository.save(adminEntity);
         return admin;
    }

}
