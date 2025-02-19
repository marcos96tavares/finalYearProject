package com.example.Admin.dto;


import com.example.Admin.entity.WeekDays;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class MuayThaiClassDto {


    /**
     * Represents the identifier for a Muay Thai class in the Data Transfer Object (DTO).
     * This variable is used to uniquely identify a specific class in the context of the Muay Thai class system.
     */
    private Long classIdDto;
    /**
     * Represents the name of a Muay Thai class in the data transfer object (DTO) structure.
     * This variable is used to hold the name of the class as part of transferring data
     * between various layers of the application or external systems.
     */
    private String classNameDto;
    /**
     * Represents the starting time of the Muay Thai class.
     * This property is used to define when a specific class begins.
     * It is stored as a LocalTime value.
     */
    private LocalTime classTimeStarDto;
    /**
     * Represents the end time of a Muay Thai class session in the DTO (Data Transfer Object) format.
     * This field is used to store the local time when the class session finishes, assisting in
     * scheduling or mapping class timings within the application layer.
     */
    private LocalTime classTimeEndDto;
    /**
     * Represents the maximum number of participants that can be accommodated in a Muay Thai class.
     */
    private int classCapacityDto;
    /**
     * Represents the day of the week a Muay Thai class is scheduled for.
     * This field uses the {@link WeekDays} enum to define the specific day.
     * The value is stored as a string in the database using the `EnumType.STRING` mapping.
     */

    private DayOfWeek weekDaysDto;



}
