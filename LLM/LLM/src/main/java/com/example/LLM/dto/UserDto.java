package com.example.LLM.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long userDtoId;
    private String firstNameDto;
    private String lastNameDto;
    private String emailDto;
    private Integer ageDto;
}
