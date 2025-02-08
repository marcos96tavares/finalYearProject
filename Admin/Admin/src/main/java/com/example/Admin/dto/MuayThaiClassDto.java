package com.example.Admin.dto;


import com.example.Admin.entity.WeekDays;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalTime;




@AllArgsConstructor
@NoArgsConstructor
public class MuayThaiClassDto {


    private Long classIdDto;
    private String classNameDto;
    private LocalTime classTimeStarDto;
    private LocalTime classTimeEndDto;
    private int classCapacityDto;
    @Enumerated(EnumType.STRING)
    private WeekDays weekDaysDto;


    public Long getClassIdDto() {
        return classIdDto;
    }

    public void setClassIdDto(Long classIdDto) {
        this.classIdDto = classIdDto;
    }

    public String getClassNameDto() {
        return classNameDto;
    }

    public void setClassNameDto(String classNameDto) {
        this.classNameDto = classNameDto;
    }

    public LocalTime getClassTimeStarDto() {
        return classTimeStarDto;
    }

    public void setClassTimeStarDto(LocalTime classTimeStarDto) {
        this.classTimeStarDto = classTimeStarDto;
    }

    public LocalTime getClassTimeEndDto() {
        return classTimeEndDto;
    }

    public void setClassTimeEndDto(LocalTime classTimeEndDto) {
        this.classTimeEndDto = classTimeEndDto;
    }

    public int getClassCapacityDto() {
        return classCapacityDto;
    }

    public void setClassCapacityDto(int classCapacityDto) {
        this.classCapacityDto = classCapacityDto;
    }

    public WeekDays getWeekDaysDto() {
        return weekDaysDto;
    }

    public void setWeekDaysDto(WeekDays weekDaysDto) {
        this.weekDaysDto = weekDaysDto;
    }
}
