package com.example.Admin.controller;


import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.service.Imp.EventGenerationService;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/muaythai/class-tracker")
public class MuayThaiClassServiceController {

    private final MuayThaiClassTrackerService trackerService;
    private final EventGenerationService eventGenerationService;

    public MuayThaiClassServiceController(MuayThaiClassTrackerService trackerService, EventGenerationService eventGenerationService) {
        this.trackerService = trackerService;
        this.eventGenerationService = eventGenerationService;
    }

    @PostMapping("/{classId}")
    public MuayThaiClassTrackerDto create(@RequestBody MuayThaiClassTrackerDto dto, @PathVariable Long classId) {
        return trackerService.createClassTracker(dto, classId);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trackerService.deleteClassTracker(id);
    }

    @GetMapping("/{id}")
    public MuayThaiClassTrackerDto getById(@PathVariable Long id) {
        return trackerService.getClassTrackerById(id);
    }

    @GetMapping( "/generate-next-class-tracker")
    public String generateNextClassTracker() {

        eventGenerationService.generateEvents();
        return "Event Generated";
    }

    @GetMapping
    public List<MuayThaiClassTrackerDto> getAll() {
        return trackerService.getAllClassTrackers();
    }

}
