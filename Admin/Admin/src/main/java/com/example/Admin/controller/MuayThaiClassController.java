package com.example.Admin.controller;

import com.example.Admin.dto.MuayThaiClassDto;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.service.Imp.MuayThaiClassServiceImp;
import com.example.Admin.service.MuayThaiClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("api/classes")
public class MuayThaiClassController {


    private MuayThaiClassService muayThaiClassService;

    public MuayThaiClassController(MuayThaiClassService muayThaiClassService) {
        this.muayThaiClassService = muayThaiClassService;
    }

    @PostMapping
    public ResponseEntity<MuayThaiClass> createClass(@RequestBody MuayThaiClassDto muayThaiClassDto) {
        MuayThaiClass createdClass = muayThaiClassService.createMuayThaiClass(muayThaiClassDto);
        if (createdClass != null) {
            return new ResponseEntity<>(createdClass, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            // Indicates class couldn't be created due to overlap
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MuayThaiClassDto> updateClass(@PathVariable Long id, @RequestBody MuayThaiClassDto muayThaiClassDto) {
        MuayThaiClassDto updatedClass = muayThaiClassService.updateMuayThaiClass(muayThaiClassDto, id);
        return new ResponseEntity<>(updatedClass, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        muayThaiClassService.deleteMuaythaiClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @GetMapping
    public ResponseEntity<List<MuayThaiClassDto>> getAllClasses() {
        List<MuayThaiClassDto> classes = muayThaiClassService.getListOfMuaythaiclasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);


    }


    @GetMapping("/{day}")
    public ResponseEntity<List<MuayThaiClassDto>> getClassWeekDays(@PathVariable String day) {

        List<MuayThaiClassDto> classes = muayThaiClassService.getListOfMuayThaiClassesByDay(day);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}
