package com.example.Client.controller;


import com.example.Client.dto.LoginDto;
import com.example.Client.dto.UserDto;
import com.example.Client.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

//    @PostMapping
//    public UserDto createUser(@RequestBody UserDto userDto) {
//        return userService.createUser(userDto);
//    }
//


    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

        UserDto userDto = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
