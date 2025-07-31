package com.example.Client.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {


    private Long userDtoId;
    private String firstNameDto;
    private String lastNameDto;
    private String emailDto;
    private String passwordDto;
    private Integer ageDto;



}
