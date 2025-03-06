package com.example.Client.service;

import com.example.Client.dto.UserDto;
import com.example.Client.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto getUserByEmail(String email);
}
