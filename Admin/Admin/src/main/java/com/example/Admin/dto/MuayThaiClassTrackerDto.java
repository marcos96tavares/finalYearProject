package com.example.Admin.dto;


import com.example.Admin.entity.MuayThaiClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MuayThaiClassTrackerDto {



    private Long classTrackerIdDto;
    private int numberPeopleAttendedClassDto;
    private int numberPeopleOnWaitListDto;
    private int numberPeopleDidNotAttendClassDto; // Corrected naming
    private MuayThaiClassDto muayThaiClassDto;


}
