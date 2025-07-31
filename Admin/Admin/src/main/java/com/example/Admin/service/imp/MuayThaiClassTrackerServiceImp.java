package com.example.Admin.service.imp;

import com.example.Admin.dto.MuayThaiClassDto;
import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.MuayThaiClassTracker;
import com.example.Admin.exception.ResourceNotFoundException;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MuayThaiClassTrackerServiceImp implements MuayThaiClassTrackerService {


    /**
     * The trackerRepository is a private final field that serves as the repository for managing
     * and persisting MuayThaiClassTracker entities. It provides an interface to perform
     * database operations such as creating, retrieving, updating, and deleting class trackers.
     */
    private final MuayThaiClassTrackerRepository trackerRepository;
    /**
     * Provides access to the functionality associated with managing
     * Muay Thai classes, including retrieving class details and
     * operations dependent on class information.
     * This variable serves as a dependency for other services that need
     * Muay Thai class-specific operations.
     */
    private final MuayThaiClassServiceImp muayThaiClassServiceImp;



    /**
     * Constructs an instance of MuayThaiClassTrackerServiceImp.
     *
     * @param trackerRepository the repository responsible for managing MuayThaiClassTracker data
     * @param muayThaiClassServiceImp the service providing operations on MuayThaiClass entities
     */
    public MuayThaiClassTrackerServiceImp(MuayThaiClassTrackerRepository trackerRepository, MuayThaiClassServiceImp muayThaiClassServiceImp) {
        this.trackerRepository = trackerRepository;
        this.muayThaiClassServiceImp = muayThaiClassServiceImp;
    }

    /**
     * Creates a Muay Thai class tracker with preset attendance, waitlist, and non-attendance values set to zero.
     * Associates the tracker with the specified Muay Thai class ID.
     *
     *  The data transfer object containing tracker details.
     * @param muayThaiClassId The ID of the Muay Thai class to associate with the tracker.
     * @return A data transfer object representing the created Muay Thai class tracker.
     */
    @Override
    public MuayThaiClassTrackerDto createClassTracker(Long muayThaiClassId) {

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


    /**
     * Adds an attendee to the specified Muay Thai class tracker if the class has available capacity.
     * If the class is full, the attendee is added to the waitlist.
     *
     * @param trackerDtoId the data transfer object representing the tracker to be updated

     * @return the updated Muay Thai class tracker as a data transfer object
     * @throws RuntimeException if the class is at full capacity
     */
    @Override
    public MuayThaiClassTrackerDto addAttend(Long trackerDtoId) {


        MuayThaiClassTracker muayThaiClassTracker = trackerRepository.findById(trackerDtoId).orElseThrow(()->new RuntimeException("MuayThaiClassTracker not found"));
        MuayThaiClass muayThaiClass = muayThaiClassTracker.getMuayThaiClass();
        int classCapacity = muayThaiClass.getClassCapacity();

        if (classCapacity <= muayThaiClassTracker.getNumberPeopleAttendedClass()) {
            muayThaiClassTracker.setNumberPeopleOnWaitList(muayThaiClassTracker.getNumberPeopleOnWaitList()+1);
            trackerRepository.save(muayThaiClassTracker);
            throw new RuntimeException("The class is full");
        }

        muayThaiClassTracker.setNumberPeopleAttendedClass(muayThaiClassTracker.getNumberPeopleAttendedClass()+1);
        trackerRepository.save(muayThaiClassTracker);
        return convertToDto(muayThaiClassTracker);

    }


    /**
     * Adds a not attend record by incrementing the number of people who did not attend
     * a specific Muay Thai class and saving the updated tracker information.
     *
     * @param trackerDtoId the data transfer object containing information about the class tracker
     * @return the updated MuayThaiClassTrackerDto object after increasing the count of people
     *         who did not attend the specified class
     */
    @Override
    public MuayThaiClassTrackerDto addNotAttend(Long trackerDtoId ) {


        MuayThaiClassTracker muayThaiClassTracker = trackerRepository.findById(trackerDtoId).orElseThrow(()->new ResourceNotFoundException("MauyThai", "id", trackerDtoId));
        muayThaiClassTracker.setNumberPeopleDidNotAttendClass(muayThaiClassTracker.getNumberPeopleDidNotAttendClass()+1);
        muayThaiClassTracker.setNumberPeopleAttendedClass(muayThaiClassTracker.getNumberPeopleAttendedClass()-1);
        return convertToDto(trackerRepository.save(muayThaiClassTracker));
    }



    /**
     * Deletes a class tracker by its ID.
     *
     * @param id the ID of the class tracker to be deleted
     */
    @Override
    public void deleteClassTracker(Long id) {
        trackerRepository.deleteById(id);
    }

    /**
     * Retrieves the details of a Muay Thai class tracker by its unique identifier.
     *
     * @param id the unique identifier of the Muay Thai class tracker to be retrieved
     * @return a {@link MuayThaiClassTrackerDto} object containing the details of the class tracker
     * @throws RuntimeException if a class tracker with the specified ID is not found
     */
    @Override
    public MuayThaiClassTrackerDto getClassTrackerById(Long id) {
        MuayThaiClassTracker tracker = trackerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class Tracker not found"));
        return convertToDto(tracker);
    }

    /**
     * Retrieves a list of all MuayThai class trackers and converts them to DTOs.
     *
     * @return a list of MuayThaiClassTrackerDto representing all class trackers.
     */
    @Override
    public List<MuayThaiClassTrackerDto> getAllClassTrackers() {
        return trackerRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public Map<DayOfWeek, List<MuayThaiClassTrackerDto>> getClassListTrackersByDay() {
        List<MuayThaiClassTracker> muayThaiClassTrackers = trackerRepository.findAll();

        // Get the current week's Monday (as the start reference point)
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        // Filter only future events (including this week)
        List<MuayThaiClassTracker> futureTrackers = muayThaiClassTrackers.stream()
                .filter(tracker -> !tracker.getEventDate().isBefore(startOfWeek)) // Exclude past events
                .sorted(Comparator.comparing(MuayThaiClassTracker::getEventDate)) // Sort by event date
                .toList();

        // Group by day of the week
        Map<DayOfWeek, List<MuayThaiClassTracker>> groupedByDay = new HashMap<>();
        for (MuayThaiClassTracker tracker : futureTrackers) {
            DayOfWeek day = tracker.getMuayThaiClass().getWeekDays();
            groupedByDay.computeIfAbsent(day, k -> new ArrayList<>()).add(tracker);
        }

        // Convert to DTOs and return
        return groupedByDay.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Keep DayOfWeek as the key
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparing(t -> t.getMuayThaiClass().getClassTimeStart())) // Sort by start time
                                .map(this::convertToDto) // Convert to DTO
                                .toList()
                ));
    }


    @Override
    public List<MuayThaiClassTrackerDto> getAllMuayThaiClassTrackersByLocalDate(LocalDate startOfWeek) {

        List<MuayThaiClassTracker> list = trackerRepository.findAllByEventDate(startOfWeek);

        return list.stream()
                .map(this::convertToDto)
                .toList();


   }







    /**
     * Converts a MuayThaiClassTracker entity into a MuayThaiClassTrackerDto object.
     *
     * @param tracker the MuayThaiClassTracker instance to be converted
     * @return a MuayThaiClassTrackerDto object containing the converted data
     */
    public MuayThaiClassTrackerDto convertToDto(MuayThaiClassTracker tracker) {
        MuayThaiClassTrackerDto dto = new MuayThaiClassTrackerDto();
        dto.setClassTrackerIdDto(tracker.getClassManagerId());

        dto.setNumberPeopleAttendedClassDto(tracker.getNumberPeopleAttendedClass());
        dto.setNumberPeopleOnWaitListDto(tracker.getNumberPeopleOnWaitList());
        dto.setNumberPeopleDidNotAttendClassDto(tracker.getNumberPeopleDidNotAttendClass());
        dto.setMuayThaiClassDto(muayThaiClassServiceImp.muayThaiClassToDto(tracker.getMuayThaiClass()));
        return dto;
    }

    public MuayThaiClassTracker convertToEntity(MuayThaiClassTrackerDto dto) {
        if (dto == null) {
            return null;
        }

        MuayThaiClassTracker tracker = new MuayThaiClassTracker();
        tracker.setClassManagerId(dto.getClassTrackerIdDto());
        tracker.setNumberPeopleAttendedClass(dto.getNumberPeopleAttendedClassDto());
        tracker.setNumberPeopleOnWaitList(dto.getNumberPeopleOnWaitListDto());
        tracker.setNumberPeopleDidNotAttendClass(dto.getNumberPeopleDidNotAttendClassDto());

        if (dto.getMuayThaiClassDto() != null) {
            tracker.setMuayThaiClass(muayThaiClassServiceImp.dtoToMuayThaiClass(dto.getMuayThaiClassDto()));
        }

        return tracker;
    }


    public void generateMuayThaiClassTracker() {

        List<MuayThaiClassDto> trackers = muayThaiClassServiceImp.getListOfMuaythaiclasses();

        for (MuayThaiClassDto tracker : trackers) {
            createClassTracker(tracker.getClassIdDto());
        }
    }


}
