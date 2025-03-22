package com.example.Admin.service.Imp;

import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import com.example.Admin.service.MemberData;
import com.example.Admin.service.MuayThaiClassService;
import com.example.Admin.service.MuayThaiClassTrackerService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Service class for generating various reports related to Muay Thai classes and their attendance.
 * This class implements the {@code MemberData} interface.
 * It provides functionality to analyze attendance rates, class popularity, active classes,
 * and overall class attendance trends using data from MuayThaiClassService and MuayThaiClassTrackerService.
 */
@Service
public class ClassReportService implements MemberData {


   private final MuayThaiClassTrackerService muayThaiClassTrackerService;
    private  final MuayThaiClassService muayThaiClassService;
    private final WebClient webClient;

    public ClassReportService(MuayThaiClassTrackerService muayThaiClassTrackerService, MuayThaiClassService muayThaiClassService, WebClient webClient) {
        this.muayThaiClassTrackerService = muayThaiClassTrackerService;
        this.muayThaiClassService = muayThaiClassService;
        this.webClient = webClient;
    }



    /**
     * Calculates and returns the total number of members.
     *
     * @return the total count of members as an integer.
     */
    @Override
    public int totalOfMembers() {

        int size = webClient.get()
                .uri("http://localhost:8081/api/membership/get_size")
                .retrieve()
                .bodyToMono(Integer.class)
                .blockOptional() // Wrap in Optional
                .orElse(0);


        return size;
    }

    /**
     * Calculates the total number of active Muay Thai classes available.
     *
     * This method retrieves the list of all Muay Thai classes from the
     * {@code MuayThaiClassService} and returns the count of those classes.
     *
     * @return the total number of active Muay Thai classes as an integer.
     */
    @Override
    public int totalActiveClases() {
        return muayThaiClassService.getListOfMuaythaiclasses().size();
    }

    /**
     * Calculates the attendance rate for all tracked Muay Thai classes.
     * The attendance rate is computed as the percentage of classes attended
     * relative to the total number of classes tracked.
     *
     * @return the attendance rate as a percentage, or 0 if there are no tracked classes.
     */
    @Override
    public double attendanceRate() {
        int totalClasses = muayThaiClassTrackerService.getAllClassTrackers().size();
        int attendedClasses = muayThaiClassTrackerService.getAllClassTrackers()
                .stream()
                .mapToInt(MuayThaiClassTrackerDto::getNumberPeopleAttendedClassDto)
                .sum();

        return totalClasses == 0 ? 0 : (double) attendedClasses / totalClasses * 100;
    }

    /**
     * Generates a ranked list of Muay Thai class names based on their popularity,
     * determined by the number of attendees for each class.
     *
     * The method retrieves attendance data for all classes and sorts them in
     * descending order of attendance. The class names are then extracted and
     * returned as a list.
     *
     * @return a list of class names ordered by their popularity, with the most
     *         attended class appearing first.
     */
    @Override
    public List<String> classPopularityRanking() {
        return muayThaiClassTrackerService.getAllClassTrackers()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getNumberPeopleAttendedClassDto(), a.getNumberPeopleAttendedClassDto()))
                .map(tracker -> tracker.getMuayThaiClassDto().getClassNameDto())
                .toList();
    }

    /**
     * Computes the attendance for all Muay Thai classes and returns it as a mapping of class names
     * to the total number of attendees.
     *
     * The method utilizes data provided by the {@code MuayThaiClassTrackerService} to calculate
     * the total attendance for each class by aggregating attendance records. If there are
     * multiple trackers for the same class, the attendance is summed.
     *
     * @return a map where the keys represent class names and the values represent the total
     *         number of people who attended each class.
     */
    @Override
    public Map<String, Integer> classAttendance() {
        return muayThaiClassTrackerService.getAllClassTrackers()
                .stream()
                .collect(Collectors.toMap(
                        tracker -> tracker.getMuayThaiClassDto().getClassNameDto(),
                        MuayThaiClassTrackerDto::getNumberPeopleAttendedClassDto,
                        Integer::sum
                ));
    }

}
