package com.example.Admin.service.Imp;

import com.example.Admin.dto.MuayThaiClassDto;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.exception.ClassAlreadyExisteException;
import com.example.Admin.exception.ResourceNotFoundException;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.service.MuayThaiClassService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MuayThaiClassServiceImp implements MuayThaiClassService {


    /**
     * Repository for managing and accessing data related to Muay Thai classes.
     * This variable serves as the data source abstraction for performing CRUD operations
     * and retrieving information about Muay Thai classes from the underlying database or
     * persistence layer.
     */
    private MuayThaiClassRepository muayThaiClassRepository;

    /**
     * Constructor for the MuayThaiClassServiceImp class.
     *
     * @param muayThaiClassRepository the repository used for accessing Muay Thai class data
     */
    public MuayThaiClassServiceImp(MuayThaiClassRepository muayThaiClassRepository) {
        this.muayThaiClassRepository = muayThaiClassRepository;
    }

    /**
     * Creates a new Muay Thai class based on the provided data transfer object.
     * The method checks for overlapping time schedules to ensure no conflicts occur.
     * If there are no overlaps, the class is created and saved to the system.
     *
     * @param muayThaiClassDto the data transfer object containing details of the Muay Thai class to be created
     * @return the created MuayThaiClass instance or an unmodified instance if the class creation fails due to scheduling conflicts
     */
    @Override
    public MuayThaiClass createMuayThaiClass(MuayThaiClassDto muayThaiClassDto) {

        MuayThaiClass muayThaiClass = new MuayThaiClass();

        //check if exist class on the same hour/ does not allow overlap time.

        if (!isOverLap(muayThaiClassDto)) {
           //convert to muaythaiclass

             muayThaiClass = dtoToMuayThaiClass(muayThaiClassDto);

            //save
            muayThaiClassRepository.save(muayThaiClass);

        }else {
            throw new ClassAlreadyExisteException("Muay Thai Class Already Exists");

        }

        // return the value

        return muayThaiClass;
    }

    /**
     * Updates the details of a Muay Thai class based on the provided DTO.
     *
     * @param muayThaiClassDto a DTO containing the details of the Muay Thai class to be updated,
     *                         including the class ID.
     * @return a DTO representing the updated Muay Thai class.
     */
    @Override
    public MuayThaiClassDto updateMuayThaiClass(MuayThaiClassDto muayThaiClassDto) {

        Long getTheId = muayThaiClassDto.getClassIdDto();

        MuayThaiClass muayThaiClass = muayThaiClassRepository.findById(getTheId).orElseThrow(
                () -> new ResourceNotFoundException("Muaythai", "id", getTheId)
        );

        return muayThaiClassToDto(muayThaiClass);
    }

    /**
     * Deletes a Muay Thai class identified by its unique ID.
     * If the class does not exist, an exception will be thrown.
     *
     * @param muaythaiClassId the unique identifier of the Muay Thai class to be deleted
     */
    @Override
    public void deleteMuaythaiClass(Long muaythaiClassId) {

        muayThaiClassRepository.findById(muaythaiClassId).orElseThrow();
        muayThaiClassRepository.deleteById(muaythaiClassId);




    }

    /**
     * Retrieves a MuayThaiClass entity by its unique identifier.
     *
     * @param id the unique identifier of the MuayThaiClass to retrieve
     * @return the MuayThaiClass associated with the given id
     * @throws  if no MuayThaiClass is found with the given id
     */
    @Override
    public MuayThaiClass getTheClassById(Long id) {
        return muayThaiClassRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Muaythai", "id", id));
    }

    /**
     * Retrieves a list of all Muay Thai classes from the repository.
     * Converts the entity objects to Data Transfer Objects (DTOs) before returning them.
     *
     * @return a list of MuayThaiClassDto objects representing the Muay Thai classes.
     */
    @Override
    public List<MuayThaiClassDto> getListOfMuaythaiclasses() {

        return muayThaiClassRepository.findAll()
                .stream()
                .map(this::muayThaiClassToDto)
                .collect(Collectors.toList());


    }


    /**
     * Converts a MuayThaiClassDto object to a MuayThaiClass object.
     *
     * @param muayThaiClassDto the DTO object containing Muay Thai class data
     * @return a MuayThaiClass object populated with data from the DTO
     */
    protected MuayThaiClass dtoToMuayThaiClass(MuayThaiClassDto muayThaiClassDto) {

        MuayThaiClass muayThaiClass = new MuayThaiClass();
        muayThaiClass.setClassName(muayThaiClassDto.getClassNameDto());
        muayThaiClass.setClassTimeStart(muayThaiClassDto.getClassTimeStarDto());
        muayThaiClass.setClassTimeEnd(muayThaiClassDto.getClassTimeEndDto());
        muayThaiClass.setClassCapacity(muayThaiClassDto.getClassCapacityDto());
        muayThaiClass.setWeekDays(muayThaiClassDto.getWeekDaysDto());


        return muayThaiClass;
    }

    /**
     * Converts a MuayThaiClass entity to a MuayThaiClassDto.
     *
     * @param muayThaiClass the MuayThaiClass entity to be converted
     * @return a MuayThaiClassDto containing the converted data
     */
    protected MuayThaiClassDto muayThaiClassToDto(MuayThaiClass muayThaiClass) {

        MuayThaiClassDto muayThaiClassDto = new MuayThaiClassDto();
        muayThaiClassDto.setClassIdDto(muayThaiClass.getClassId());
        muayThaiClassDto.setClassNameDto(muayThaiClass.getClassName());
        muayThaiClassDto.setClassTimeStarDto(muayThaiClass.getClassTimeStart());
        muayThaiClassDto.setClassTimeEndDto(muayThaiClass.getClassTimeEnd());
        muayThaiClassDto.setClassCapacityDto(muayThaiClass.getClassCapacity());
        muayThaiClassDto.setWeekDaysDto(muayThaiClass.getWeekDays());


        return muayThaiClassDto;
    }


    /**
     * Checks whether the given Muay Thai class overlaps with any existing classes.
     *
     * @param muayThaiClassDto the details of the Muay Thai class to check for overlap, including its
     *                         scheduled day and start/end times
     * @return true if there is an overlap with any existing Muay Thai class on the same day and time;
     *         false otherwise
     */
    private Boolean isOverLap(MuayThaiClassDto muayThaiClassDto) {


        // Retrieve all Muay Thai classes
        List<MuayThaiClassDto> getAllMuayThaiClasses = getListOfMuaythaiclasses();


        for (MuayThaiClassDto m : getAllMuayThaiClasses) {
            // Check if the class is on the same day
            if (m.getWeekDaysDto().equals(muayThaiClassDto.getWeekDaysDto())) {
                LocalTime start = m.getClassTimeStarDto();
                LocalTime end = m.getClassTimeEndDto();

                // Check for overlap
                if ((muayThaiClassDto.getClassTimeStarDto().isBefore(end) &&
                        muayThaiClassDto.getClassTimeEndDto().isAfter(start)) ||
                        (muayThaiClassDto.getClassTimeStarDto().equals(start) &&
                                muayThaiClassDto.getClassTimeEndDto().equals(end))) {
                    return true; // Overlap found
                }
            }


        }

        return false;
    }

}
