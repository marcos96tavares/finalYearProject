package com.example.Admin.service;

import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.entity.MuayThaiClass;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface MuayThaiClassTrackerService {

    MuayThaiClassTrackerDto createClassTracker( Long MuayThaiClassId);

    void deleteClassTracker(Long id);
    MuayThaiClassTrackerDto getClassTrackerById(Long id);
    List<MuayThaiClassTrackerDto> getAllClassTrackers();
    Map<DayOfWeek, List<MuayThaiClassTrackerDto>> getClassListTrackersByDay();








}
