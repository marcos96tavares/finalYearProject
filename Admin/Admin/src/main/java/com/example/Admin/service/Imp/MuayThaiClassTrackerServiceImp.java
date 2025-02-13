package com.example.Admin.service.Imp;

import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.MuayThaiClassTracker;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuayThaiClassTrackerServiceImp implements MuayThaiClassTrackerService {


    private final MuayThaiClassTrackerRepository trackerRepository;
    private final MuayThaiClassServiceImp muayThaiClassServiceImp;

    public MuayThaiClassTrackerServiceImp(MuayThaiClassTrackerRepository trackerRepository, MuayThaiClassServiceImp muayThaiClassServiceImp) {
        this.trackerRepository = trackerRepository;
        this.muayThaiClassServiceImp = muayThaiClassServiceImp;
    }

    @Override
    public MuayThaiClassTrackerDto createClassTracker(MuayThaiClassTrackerDto trackerDto, Long muayThaiClassId) {

        MuayThaiClass muayThaiClass = muayThaiClassServiceImp.getTheClassById(muayThaiClassId);

        int attended = 0;
        int notAttended = 0;
        int waitList = 0;




        MuayThaiClassTracker tracker = new MuayThaiClassTracker();
        tracker.setNumberPeopleAttendedClass(attended);
        tracker.setNumberPeopleOnWaitList(waitList);
        tracker.setNumberPeopleDidNotAttendClass(notAttended);
        tracker.setMuayThaiClass(muayThaiClass);

        return convertToDto(trackerRepository.save(tracker));

    }

    @Override
    public MuayThaiClassTrackerDto updateClassTracker(Long muayThaiClassId, MuayThaiClassTrackerDto trackerDto) {

        MuayThaiClass muayThaiClass = muayThaiClassServiceImp.getTheClassById(muayThaiClassId);
        int classCapacity = muayThaiClass.getClassCapacity()+1;

        if (classCapacity <= trackerDto.getNumberPeopleAttendedClassDto()) throw new RuntimeException("You can book Now");


        MuayThaiClassTracker tracker = trackerRepository.findById(trackerDto.getClassTrackerIdDto())
                .orElseThrow(() -> new RuntimeException("Class Tracker not found"));

        tracker.setNumberPeopleAttendedClass(trackerDto.getNumberPeopleAttendedClassDto());
        tracker.setNumberPeopleOnWaitList(trackerDto.getNumberPeopleOnWaitListDto());
        tracker.setNumberPeopleDidNotAttendClass(trackerDto.getNumberPeopleDidNotAttendClassDto());
        tracker.setMuayThaiClass(muayThaiClass);
        return convertToDto(trackerRepository.save(tracker));
    }

    @Override
    public void deleteClassTracker(Long id) {
        trackerRepository.deleteById(id);
    }

    @Override
    public MuayThaiClassTrackerDto getClassTrackerById(Long id) {
        MuayThaiClassTracker tracker = trackerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class Tracker not found"));
        return convertToDto(tracker);
    }

    @Override
    public List<MuayThaiClassTrackerDto> getAllClassTrackers() {
        return trackerRepository.findAll().stream().map(this::convertToDto).toList();
    }



    private MuayThaiClassTrackerDto convertToDto(MuayThaiClassTracker tracker) {
        MuayThaiClassTrackerDto dto = new MuayThaiClassTrackerDto();
        dto.setClassTrackerIdDto(tracker.getClassManagerId());

        dto.setNumberPeopleAttendedClassDto(tracker.getNumberPeopleAttendedClass());
        dto.setNumberPeopleOnWaitListDto(tracker.getNumberPeopleOnWaitList());
        dto.setNumberPeopleDidNotAttendClassDto(tracker.getNumberPeopleDidNotAttendClass());
        dto.setMuayThaiClassDto(tracker.getMuayThaiClass());
        return dto;
    }
}
