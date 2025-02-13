package com.example.Admin.controller;


import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/muaythai/class-tracker")
public class MuayThaiClassServiceController {

    private final MuayThaiClassTrackerService trackerService;

    public MuayThaiClassServiceController(MuayThaiClassTrackerService trackerService) {
        this.trackerService = trackerService;
    }

    @PostMapping("/{classId}")
    public MuayThaiClassTrackerDto create(@RequestBody MuayThaiClassTrackerDto dto, @PathVariable Long classId) {
        return trackerService.createClassTracker(dto, classId);
    }

    @PutMapping("/{classId}")
    public MuayThaiClassTrackerDto update(@PathVariable Long classId, @RequestBody MuayThaiClassTrackerDto dto) {
        return trackerService.updateClassTracker(classId, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trackerService.deleteClassTracker(id);
    }

    @GetMapping("/{id}")
    public MuayThaiClassTrackerDto getById(@PathVariable Long id) {
        return trackerService.getClassTrackerById(id);
    }

    @GetMapping
    public List<MuayThaiClassTrackerDto> getAll() {
        return trackerService.getAllClassTrackers();
    }

}
