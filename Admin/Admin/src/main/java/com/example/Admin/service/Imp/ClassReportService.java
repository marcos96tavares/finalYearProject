package com.example.Admin.service.Imp;

import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import com.example.Admin.service.MemberData;
import com.example.Admin.service.MuayThaiClassService;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ClassReportService implements MemberData {


   private final MuayThaiClassTrackerService muayThaiClassTrackerService;
    private  final MuayThaiClassService muayThaiClassService;

    public ClassReportService(MuayThaiClassTrackerService muayThaiClassTrackerService, MuayThaiClassService muayThaiClassService) {
        this.muayThaiClassTrackerService = muayThaiClassTrackerService;
        this.muayThaiClassService = muayThaiClassService;
    }

    @Override
    public int totalOfMembers() {
        return 0;
    }

    @Override
    public int totalActiveClases() {
        return muayThaiClassService.getListOfMuaythaiclasses().size();
    }

    @Override
    public double attendanceRate() {
        int totalClasses = muayThaiClassTrackerService.getAllClassTrackers().size();
        int attendedClasses = muayThaiClassTrackerService.getAllClassTrackers()
                .stream()
                .mapToInt(MuayThaiClassTrackerDto::getNumberPeopleAttendedClassDto)
                .sum();

        return totalClasses == 0 ? 0 : (double) attendedClasses / totalClasses * 100;
    }

    @Override
    public List<String> classPopularityRanking() {
        return muayThaiClassTrackerService.getAllClassTrackers()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getNumberPeopleAttendedClassDto(), a.getNumberPeopleAttendedClassDto()))
                .map(tracker -> tracker.getMuayThaiClassDto().getClassName())
                .toList();
    }

    @Override
    public Map<String, Integer> classAttendance() {
        return muayThaiClassTrackerService.getAllClassTrackers()
                .stream()
                .collect(Collectors.toMap(
                        tracker -> tracker.getMuayThaiClassDto().getClassName(),
                        MuayThaiClassTrackerDto::getNumberPeopleAttendedClassDto,
                        Integer::sum
                ));
    }

}
