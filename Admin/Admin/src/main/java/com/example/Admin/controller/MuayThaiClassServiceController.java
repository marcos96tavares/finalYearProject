package com.example.Admin.controller;


import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.service.imp.EventGenerationService;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/class-tracker")
public class MuayThaiClassServiceController {

    private final MuayThaiClassTrackerService trackerService;
    private final EventGenerationService eventGenerationService;

    public MuayThaiClassServiceController(MuayThaiClassTrackerService trackerService, EventGenerationService eventGenerationService) {
        this.trackerService = trackerService;
        this.eventGenerationService = eventGenerationService;
    }

    @PostMapping("/{classId}")
    public MuayThaiClassTrackerDto create( @PathVariable Long classId) {
        return trackerService.createClassTracker( classId);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trackerService.deleteClassTracker(id);
    }

    @GetMapping("/id-{id}")
    public MuayThaiClassTrackerDto getById(@PathVariable Long id) {
        return trackerService.getClassTrackerById(id);
    }

    @GetMapping( "/generate-next-class-tracker")
    public String generateNextClassTracker() {

        eventGenerationService.generateEvents();
        return "Event Generated";
    }



    @PostMapping("generate/{id}")
    public void generateByClassId(@PathVariable Long id) {
        eventGenerationService.generateNextClassesByClassId(id);
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


    @GetMapping("/{date}")
    public ResponseEntity<List<MuayThaiClassTrackerDto>> getTrackersByDate(@PathVariable String date) {

        LocalDate parsedDate = LocalDate.parse(date);
        List<MuayThaiClassTrackerDto> tracker = trackerService.getAllMuayThaiClassTrackersByLocalDate(parsedDate);
        return new ResponseEntity<>(tracker, HttpStatus.OK);

    }



    @PostMapping("add-attend/{trackerId}")
    public String addAttened(@PathVariable("trackerId") Long trackerId) {

        MuayThaiClassTrackerDto muayThaiClassTrackerDto  = trackerService.addAttend(trackerId);
        return STR."Added attendment to tracker \{trackerId}";
    }

    @PostMapping("no-attend/{trackerId}")
    public String noAttened(@PathVariable("trackerId") Long tracker) {

        MuayThaiClassTrackerDto muayThaiClassTrackerDto  = trackerService.addNotAttend(tracker);
        return STR."No Attended the class \{tracker}";
    }


}
