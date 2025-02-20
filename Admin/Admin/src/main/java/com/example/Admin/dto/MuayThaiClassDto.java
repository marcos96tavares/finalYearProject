package com.example.Admin.dto;


import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class MuayThaiClassDto {



    private Long classIdDto;
    private String classNameDto;
    private LocalTime classTimeStarDto;
    private LocalTime classTimeEndDto;
    private int classCapacityDto;
    private DayOfWeek weekDaysDto;



}
