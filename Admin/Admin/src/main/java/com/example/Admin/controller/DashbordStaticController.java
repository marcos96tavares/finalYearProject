package com.example.Admin.controller;


import com.example.Admin.service.MemberData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/static")
public class DashbordStaticController {


    private final MemberData memberData;

    public DashbordStaticController(MemberData memberData) {
        this.memberData = memberData;
    }



    @GetMapping("/members")
    public ResponseEntity<Integer> getNumberOfMembers() {
        Integer number = memberData.totalOfMembers();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("/classes")
    public ResponseEntity<Integer> getNumberOfClasses() {
        Integer number = memberData.totalActiveClases();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("/rate")
    public ResponseEntity<Double> getAttendanceRate() {

        Double number = memberData.attendanceRate();
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("classAttendance")
    public ResponseEntity<Map<String, Integer>> getClassAttendance() {
        Map<String, Integer> classAttendance = memberData.classAttendance();
        return new ResponseEntity<>(classAttendance, HttpStatus.OK);
    }


}
