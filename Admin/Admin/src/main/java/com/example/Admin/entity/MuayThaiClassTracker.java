package com.example.Admin.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MuayThaiClassTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classManagerId;

    @Enumerated(EnumType.STRING)
    private ClassStatus classStatus;  // Now properly defined

    private int numberPeopleAttendedClass;
    private int numberPeopleOnWaitList;
    private int numberPeopleDidNotAttendClass; // Corrected naming

    @ManyToOne
    @JoinColumn(name = "muay_thai_class_id", nullable = false)  // Explicit FK name
    private MuayThaiClass muayThaiClass;

    public Long getClassManagerId() {
        return classManagerId;
    }

    public void setClassManagerId(Long classManagerId) {
        this.classManagerId = classManagerId;
    }

    public ClassStatus getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(ClassStatus classStatus) {
        this.classStatus = classStatus;
    }

    public int getNumberPeopleAttendedClass() {
        return numberPeopleAttendedClass;
    }

    public void setNumberPeopleAttendedClass(int numberPeopleAttendedClass) {
        this.numberPeopleAttendedClass = numberPeopleAttendedClass;
    }

    public int getNumberPeopleOnWaitList() {
        return numberPeopleOnWaitList;
    }

    public void setNumberPeopleOnWaitList(int numberPeopleOnWaitList) {
        this.numberPeopleOnWaitList = numberPeopleOnWaitList;
    }

    public int getNumberPeopleDidNotAttendClass() {
        return numberPeopleDidNotAttendClass;
    }

    public void setNumberPeopleDidNotAttendClass(int numberPeopleDidNotAttendClass) {
        this.numberPeopleDidNotAttendClass = numberPeopleDidNotAttendClass;
    }

    public MuayThaiClass getMuayThaiClass() {
        return muayThaiClass;
    }

    public void setMuayThaiClass(MuayThaiClass muayThaiClass) {
        this.muayThaiClass = muayThaiClass;
    }
}
