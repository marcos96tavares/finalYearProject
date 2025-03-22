package com.example.Admin.service;

import com.example.Admin.dto.MuayThaiClassDto;
import com.example.Admin.entity.MuayThaiClass;

import java.time.DayOfWeek;
import java.util.List;

public interface MuayThaiClassService {

    MuayThaiClass createMuayThaiClass(MuayThaiClassDto muayThaiClassDto);

    MuayThaiClassDto updateMuayThaiClass(MuayThaiClassDto muayThaiClassDto , Long classId);

    void deleteMuaythaiClass(Long muaythaiClassId);

    MuayThaiClass getTheClassById(Long id);


    List<MuayThaiClassDto> getListOfMuaythaiclasses();
    List<MuayThaiClassDto> getListOfMuayThaiClassesByDay(String day);




}
