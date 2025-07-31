package com.example.Client.service.impl;

import com.example.Client.dto.UserDto;
import com.example.Client.entity.User;
import com.example.Client.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImpTest {


    @InjectMocks
     UserServiceImp userService;

     @Mock
     private UserRepository userRepository;
     @Mock
     private PasswordEncoder passwordEncoder;





    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_save_createUser() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setUserDtoId(1L);
        userDto.setFirstNameDto("John");
        userDto.setLastNameDto("Doe");
        userDto.setEmailDto("john.doe@example.com");
        userDto.setAgeDto(30);
        userDto.setPasswordDto("plaintextpassword");

        String encodedPassword = "encodedPassword123";

        // Mock password encoder
        when(passwordEncoder.encode("plaintextpassword")).thenReturn(encodedPassword);

        // Expected user (what we expect to be passed to the repo and returned)
        User expectedUser = new User();
        expectedUser.setUserId(1L);
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Doe");
        expectedUser.setEmail("john.doe@example.com");
        expectedUser.setAge(30);
        expectedUser.setPassword(encodedPassword);

        // Mock repository save
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        // When
        User savedUser = userService.createUser(userDto);

        // Then
        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getUserId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("john.doe@example.com", savedUser.getEmail());
        assertEquals(30, savedUser.getAge());
        assertEquals(encodedPassword, savedUser.getPassword());
    }

    @Test
    public void Should_getAllUsers() {
        // Given (Mock data and repository)
        User user = new User();
        user.setUserId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setAge(30);
        user.setPassword("plaintextpassword");

        List<User> userList = new ArrayList<>(List.of(user));
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<UserDto> result = userService.getAllUsers();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());

        UserDto userDto = result.get(0);
        assertEquals(1L, userDto.getUserDtoId());
        assertEquals("John", userDto.getFirstNameDto());
        assertEquals("Doe", userDto.getLastNameDto());
        assertEquals("john.doe@example.com", userDto.getEmailDto());
        assertEquals(30, userDto.getAgeDto());

    }


    @Test
    public void should_updateUser() {

        //Give


    }


}