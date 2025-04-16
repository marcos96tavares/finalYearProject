package com.example.Client.controller;

import com.example.Client.dto.AdminDto;
import com.example.Client.entity.Admin;
import com.example.Client.service.impl.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/register")
public class AdminControllerRegister {

    private final AdminService adminService;

    public AdminControllerRegister(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping
    public ResponseEntity<AdminDto> registerAdmin(@RequestBody AdminDto admin) {
        adminService.CreateAdmin(admin);
       return ResponseEntity.ok(admin);
    }
}
