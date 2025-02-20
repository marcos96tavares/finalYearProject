package com.example.Admin.service;

import com.example.Admin.dto.MuayThaiClassTrackerDto;

import java.util.List;

public interface MuayThaiClassTrackerService {

    MuayThaiClassTrackerDto createClassTracker(MuayThaiClassTrackerDto trackerDto, Long MuayThaiClassId);

    void deleteClassTracker(Long id);
    MuayThaiClassTrackerDto getClassTrackerById(Long id);
    List<MuayThaiClassTrackerDto> getAllClassTrackers();






}
