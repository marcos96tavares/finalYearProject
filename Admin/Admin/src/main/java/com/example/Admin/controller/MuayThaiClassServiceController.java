package com.example.Admin.controller;


import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.service.Imp.EventGenerationService;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

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

    @GetMapping( "/data")
    public void generateNextBankHolidays() {

        eventGenerationService.getBankHolidayDays();

    }

    @GetMapping
    public List<MuayThaiClassTrackerDto> getAll() {
        return trackerService.getAllClassTrackers();
    }



    @GetMapping("/get-all-Tracker")
    public ResponseEntity< Map<DayOfWeek,List <MuayThaiClassTrackerDto>>> getAllTrackers() {

        Map<DayOfWeek, List<MuayThaiClassTrackerDto>> map = trackerService.getClassListTrackersByDay();

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
