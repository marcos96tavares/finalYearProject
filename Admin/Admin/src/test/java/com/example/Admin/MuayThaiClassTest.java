package com.example.Admin;


import com.example.Admin.dto.MuayThaiClassDto;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.WeekDays;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.service.Imp.MuayThaiClassServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MuayThaiClassTest {

    @Mock
    private MuayThaiClassRepository muayThaiClassRepository;

    @InjectMocks
    private MuayThaiClassServiceImp muayThaiClassService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMuayThaiClassNoOverlap() {
        List<MuayThaiClass> classes = new ArrayList<>();

        MuayThaiClass existingClass = new MuayThaiClass();
        existingClass.setClassId(1L);
        existingClass.setClassName("Class 1");
        existingClass.setClassTimeStart(LocalTime.of(10, 0));
        existingClass.setClassTimeEnd(LocalTime.of(12, 0));
        existingClass.setClassCapacity(20);
        existingClass.setWeekDays(WeekDays.MON);
        classes.add(existingClass);

        when(muayThaiClassRepository.findAll()).thenReturn(classes);

        MuayThaiClassDto newClass = new MuayThaiClassDto();
        newClass.setClassIdDto(1L);
        newClass.setClassNameDto("New Class");
        newClass.setClassTimeStarDto(LocalTime.of(12, 0));
        newClass.setClassTimeEndDto(LocalTime.of(14, 0));
        newClass.setClassCapacityDto(20);
        newClass.setWeekDaysDto(WeekDays.MON);

        MuayThaiClass createdClass = muayThaiClassService.createMuayThaiClass(newClass);

        assertEquals(newClass.getClassNameDto(), createdClass.getClassName());
    }

    @Test
    void testCreateMuayThaiClassOverlap() {
        List<MuayThaiClass> classes = new ArrayList<>();

        MuayThaiClass existingClass = new MuayThaiClass();
        existingClass.setClassId(1L);
        existingClass.setClassName("Class 1");
        existingClass.setClassTimeStart(LocalTime.of(10, 0));
        existingClass.setClassTimeEnd(LocalTime.of(12, 0));
        existingClass.setClassCapacity(20);
        existingClass.setWeekDays(WeekDays.MON);
        classes.add(existingClass);

        when(muayThaiClassRepository.findAll()).thenReturn(classes);

        MuayThaiClassDto newClass = new MuayThaiClassDto();
        newClass.setClassIdDto(1L);
        newClass.setClassNameDto("New Class");
        newClass.setClassTimeStarDto(LocalTime.of(11, 0));
        newClass.setClassTimeEndDto(LocalTime.of(13, 0));
        newClass.setClassCapacityDto(20);
        newClass.setWeekDaysDto(WeekDays.MON);

        MuayThaiClass createdClass = muayThaiClassService.createMuayThaiClass(newClass);

        assertNull(createdClass); // Class should not be created due to overlap
    }

    @Test
    void testCreateMuayThaiClassDifferentDay() {
        List<MuayThaiClass> classes = new ArrayList<>();

        MuayThaiClass existingClass = new MuayThaiClass();
        existingClass.setClassId(1L);
        existingClass.setClassName("Class 1");
        existingClass.setClassTimeStart(LocalTime.of(10, 0));
        existingClass.setClassTimeEnd(LocalTime.of(12, 0));
        existingClass.setClassCapacity(20);
        existingClass.setWeekDays(WeekDays.MON);
        classes.add(existingClass);

        when(muayThaiClassRepository.findAll()).thenReturn(classes);

        MuayThaiClassDto newClass = new MuayThaiClassDto();
        newClass.setClassIdDto(1L);
        newClass.setClassNameDto("New Class");
        newClass.setClassTimeStarDto(LocalTime.of(11, 0));
        newClass.setClassTimeEndDto(LocalTime.of(13, 0));
        newClass.setClassCapacityDto(20);
        newClass.setWeekDaysDto(WeekDays.TUES);

        MuayThaiClass createdClass = muayThaiClassService.createMuayThaiClass(newClass);

        assertEquals(newClass.getClassNameDto(), createdClass.getClassName());
    }
}