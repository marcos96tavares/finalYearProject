package com.example.Admin.dto;


import com.example.Admin.entity.MuayThaiClass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class MuayThaiClassTrackerDto {



    private Long classTrackerIdDto;


    private int numberPeopleAttendedClassDto;
    private int numberPeopleOnWaitListDto;
    private int numberPeopleDidNotAttendClassDto; // Corrected naming

    private MuayThaiClass muayThaiClassDto;



    public Long getClassTrackerIdDto() {
        return classTrackerIdDto;
    }

    public void setClassTrackerIdDto(Long classTrackerIdDto) {
        this.classTrackerIdDto = classTrackerIdDto;
    }



    public int getNumberPeopleAttendedClassDto() {
        return numberPeopleAttendedClassDto;
    }

    public void setNumberPeopleAttendedClassDto(int numberPeopleAttendedClassDto) {
        this.numberPeopleAttendedClassDto = numberPeopleAttendedClassDto;
    }

    public int getNumberPeopleOnWaitListDto() {
        return numberPeopleOnWaitListDto;
    }

    public void setNumberPeopleOnWaitListDto(int numberPeopleOnWaitListDto) {
        this.numberPeopleOnWaitListDto = numberPeopleOnWaitListDto;
    }

    public int getNumberPeopleDidNotAttendClassDto() {
        return numberPeopleDidNotAttendClassDto;
    }

    public void setNumberPeopleDidNotAttendClassDto(int numberPeopleDidNotAttendClassDto) {
        this.numberPeopleDidNotAttendClassDto = numberPeopleDidNotAttendClassDto;
    }

    public MuayThaiClass getMuayThaiClassDto() {
        return muayThaiClassDto;
    }

    public void setMuayThaiClassDto(MuayThaiClass muayThaiClassDto) {
        this.muayThaiClassDto = muayThaiClassDto;
    }
}
