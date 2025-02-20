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
     * @param trackerDto The data transfer object containing tracker details.
     * @param muayThaiClassId The ID of the Muay Thai class to associate with the tracker.
     * @return A data transfer object representing the created Muay Thai class tracker.
     */
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


    /**
     * Adds an attendee to the specified Muay Thai class tracker if the class has available capacity.
     * If the class is full, the attendee is added to the waitlist.
     *
     * @param trackerDto the data transfer object representing the tracker to be updated
     * @param muayThaiClassId the ID of the Muay Thai class to which attendance is being added
     * @return the updated Muay Thai class tracker as a data transfer object
     * @throws RuntimeException if the class is at full capacity
     */
    public MuayThaiClassTrackerDto addAttend(MuayThaiClassTrackerDto trackerDto, Long muayThaiClassId) {

        MuayThaiClass muayThaiClass = muayThaiClassServiceImp.getTheClassById(muayThaiClassId);
        MuayThaiClassTracker muayThaiClassTracker = trackerRepository.findById(trackerDto.getClassTrackerIdDto()).orElseThrow();
        int classCapacity = muayThaiClass.getClassCapacity();

        if (classCapacity <= trackerDto.getNumberPeopleAttendedClassDto()) {
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
     * @param trackerDto the data transfer object containing information about the class tracker
     * @param muayThaiClassId the unique ID of the Muay Thai class
     * @return the updated MuayThaiClassTrackerDto object after increasing the count of people
     *         who did not attend the specified class
     */
    public MuayThaiClassTrackerDto addNotAttend(MuayThaiClassTrackerDto trackerDto, Long muayThaiClassId) {

        MuayThaiClass muayThaiClass = muayThaiClassServiceImp.getTheClassById(muayThaiClassId);
        MuayThaiClassTracker muayThaiClassTracker = trackerRepository.findById(trackerDto.getClassTrackerIdDto()).orElseThrow();
        muayThaiClassTracker.setNumberPeopleDidNotAttendClass(muayThaiClassTracker.getNumberPeopleDidNotAttendClass()+1);
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



    /**
     * Converts a MuayThaiClassTracker entity into a MuayThaiClassTrackerDto object.
     *
     * @param tracker the MuayThaiClassTracker instance to be converted
     * @return a MuayThaiClassTrackerDto object containing the converted data
     */
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
