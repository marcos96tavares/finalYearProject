package com.example.Admin.service;

import com.example.Admin.dto.MuayThaiClassTrackerDto;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MuayThaiClassTrackerService {

    MuayThaiClassTrackerDto createClassTracker( Long MuayThaiClassId);

    void deleteClassTracker(Long id);
    MuayThaiClassTrackerDto getClassTrackerById(Long id);
    List<MuayThaiClassTrackerDto> getAllClassTrackers();
    Map<DayOfWeek, List<MuayThaiClassTrackerDto>> getClassListTrackersByDay();

    List<MuayThaiClassTrackerDto>getAllMuayThaiClassTrackersByLocalDate(LocalDate startOfWeek);

    MuayThaiClassTrackerDto addAttend(Long muayThaiClassId);
    MuayThaiClassTrackerDto addNotAttend(Long muayThaiClassId);







}
