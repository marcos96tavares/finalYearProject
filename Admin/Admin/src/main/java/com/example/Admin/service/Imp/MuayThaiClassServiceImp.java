package com.example.Admin.service.Imp;

import com.example.Admin.dto.MuayThaiClassDto;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.service.MuayThaiClassService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MuayThaiClassServiceImp implements MuayThaiClassService {


    private MuayThaiClassRepository muayThaiClassRepository;

    public MuayThaiClassServiceImp(MuayThaiClassRepository muayThaiClassRepository) {
        this.muayThaiClassRepository = muayThaiClassRepository;
    }

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
            System.out.println("You can not create this Class!");
        }

        // return the value

        return muayThaiClass;
    }

    @Override
    public MuayThaiClassDto updateMuayThaiClass(MuayThaiClassDto muayThaiClassDto) {

        Long getTheId = muayThaiClassDto.getClassIdDto();

        MuayThaiClass muayThaiClass = muayThaiClassRepository.findById(getTheId).orElseThrow();

        return muayThaiClassToDto(muayThaiClass);
    }

    @Override
    public void deleteMuaythaiClass(Long muaythaiClassId) {

        muayThaiClassRepository.findById(muaythaiClassId).orElseThrow();
        muayThaiClassRepository.deleteById(muaythaiClassId);




    }

    @Override
    public MuayThaiClass getTheClassById(Long id) {
        return muayThaiClassRepository.findById(id).orElseThrow();
    }

    @Override
    public List<MuayThaiClassDto> getListOfMuaythaiclasses() {

        return muayThaiClassRepository.findAll()
                .stream()
                .map(this::muayThaiClassToDto)
                .collect(Collectors.toList());


    }


    protected MuayThaiClass dtoToMuayThaiClass(MuayThaiClassDto muayThaiClassDto) {

        MuayThaiClass muayThaiClass = new MuayThaiClass();
        muayThaiClass.setClassName(muayThaiClassDto.getClassNameDto());
        muayThaiClass.setClassTimeStart(muayThaiClassDto.getClassTimeStarDto());
        muayThaiClass.setClassTimeEnd(muayThaiClassDto.getClassTimeEndDto());
        muayThaiClass.setClassCapacity(muayThaiClassDto.getClassCapacityDto());
        muayThaiClass.setWeekDays(muayThaiClassDto.getWeekDaysDto());


        return muayThaiClass;
    }

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
