package com.example.Admin.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MuayThaiClass {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    private String className;
    private LocalTime classTimeStart;  // Represents class time (e.g., 14:00)
    private LocalTime classTimeEnd;
    private DayOfWeek weekDays;

    private int classCapacity;

    @OneToMany(mappedBy = "muayThaiClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MuayThaiClassTracker> muayThaiManagers;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalTime getClassTimeStart() {
        return classTimeStart;
    }

    public void setClassTimeStart(LocalTime classTimeStart) {
        this.classTimeStart = classTimeStart;
    }

    public LocalTime getClassTimeEnd() {
        return classTimeEnd;
    }

    public void setClassTimeEnd(LocalTime classTimeEnd) {
        this.classTimeEnd = classTimeEnd;
    }

    public DayOfWeek getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(DayOfWeek weekDays) {
        this.weekDays = weekDays;
    }

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    public List<MuayThaiClassTracker> getMuayThaiManagers() {
        return muayThaiManagers;
    }

    public void setMuayThaiManagers(List<MuayThaiClassTracker> muayThaiManagers) {
        this.muayThaiManagers = muayThaiManagers;
    }
}

