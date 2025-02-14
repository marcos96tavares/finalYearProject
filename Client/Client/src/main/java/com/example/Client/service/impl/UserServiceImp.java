package com.example.Client.service.impl;

import com.example.Client.dto.UserDto;
import com.example.Client.entity.User;
import com.example.Client.repository.UserRepository;
import com.example.Client.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {


    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = convertToEntity(userDto);
        return convertToDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userDto.getFirstNameDto());
        user.setLastName(userDto.getLastNameDto());
        user.setEmail(userDto.getEmailDto());

        return convertToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstNameDto());
        user.setLastName(userDto.getLastNameDto());
        user.setEmail(userDto.getEmailDto());
        return user;
    }


    private UserDto convertToDto(User user) {
        return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
