package com.example.Client.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Long userDtoId;
    private String firstNameDto;
    private String lastNameDto;
    private String emailDto;

    public Long getUserDtoId() {
        return userDtoId;
    }

    public void setUserDtoId(Long userDtoId) {
        this.userDtoId = userDtoId;
    }

    public String getFirstNameDto() {
        return firstNameDto;
    }

    public void setFirstNameDto(String firstNameDto) {
        this.firstNameDto = firstNameDto;
    }

    public String getLastNameDto() {
        return lastNameDto;
    }

    public void setLastNameDto(String lastNameDto) {
        this.lastNameDto = lastNameDto;
    }

    public String getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(String emailDto) {
        this.emailDto = emailDto;
    }
}
