package com.example.Admin.service.Imp;


import com.example.Admin.dto.BankHolidayEvent;
import com.example.Admin.dto.BankHolidayResponse;
import com.example.Admin.entity.BankHolidayDays;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.MuayThaiClassTracker;
import com.example.Admin.repository.BankHolidayRepository;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sound.midi.Soundbank;
import java.beans.Transient;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventGenerationService {

    /**
     * Repository for performing CRUD operations on the MuayThaiClass entity.
     * This repository is used for accessing Muay Thai class data stored in the database.
     */
    private final MuayThaiClassRepository muayThaiClassRepository;
    /**
     * Repository for managing {@link MuayThaiClassTracker} entities.
     * This is used to perform database operations related to tracking information
     * for Muay Thai class events, such as checking the existence of trackers and saving new ones.
     */
    private final MuayThaiClassTrackerRepository muayThaiClassTrackerRepository;

    private final WebClient webClient;
    private final BankHolidayRepository bankHolidayRepository;

    /**
     * Constructs an EventGenerationService.
     *
     * @param muayThaiClassRepository        the repository used to manage Muay Thai class entities
     * @param muayThaiClassTrackerRepository the repository used to manage Muay Thai class tracker entities
     */
    public EventGenerationService(MuayThaiClassRepository muayThaiClassRepository, MuayThaiClassTrackerRepository muayThaiClassTrackerRepository, WebClient webClient, BankHolidayRepository bankHolidayRepository) {
        this.muayThaiClassRepository = muayThaiClassRepository;
        this.muayThaiClassTrackerRepository = muayThaiClassTrackerRepository;
        this.webClient = webClient;
        this.bankHolidayRepository = bankHolidayRepository;
    }

    /**
     * Generates tracker records for scheduled Muay Thai classes for the upcoming week.
     * <p>
     * This method retrieves all existing Muay Thai classes from the database and computes
     * the next event date based on the specified weekday for each class. It checks whether
     * a tracker entry already exists for each class on the calculated date in the next week.
     * If no tracker exists for that date, a new tracker is created and stored in the database
     * with initial attendance statistics set to zero.
     * <p>
     * The method ensures that new tracker entries are only created for classes that do not
     * already have existing trackers for the specified week.
     * <p>
     * Transactional behavior is applied to ensure that database operations are executed atomically.
     */
    @Transactional
    public void generateEvents() {
        List<MuayThaiClass> allClasses = muayThaiClassRepository.findAll();

        // Fetch bank holidays once and store them in a Set for quick lookup
        Set<LocalDate> bankHolidays = bankHolidayRepository.findAll()
                .stream()
                .map(BankHolidayDays::getDate)
                .collect(Collectors.toSet());

        if (bankHolidays.isEmpty()) {
            getBankHolidayDays(); // Fetch if no bank holidays exist
            bankHolidays = bankHolidayRepository.findAll()
                    .stream()
                    .map(BankHolidayDays::getDate)
                    .collect(Collectors.toSet());
        }

        // Get next week's Monday
        LocalDate nextWeekStart = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);

        for (MuayThaiClass muayThaiClass : allClasses) {

            LocalDate nextEventDate = nextWeekStart.with(muayThaiClass.getWeekDays());

            // Skip if it's a bank holiday
            if (bankHolidays.contains(nextEventDate)) {
                continue;
            }

            // Check if tracker already exists
            boolean exists = muayThaiClassTrackerRepository.existsByMuayThaiClassAndEventDate(muayThaiClass, nextEventDate);
            if (!exists) {
                MuayThaiClassTracker newTracker = new MuayThaiClassTracker();
                newTracker.setMuayThaiClass(muayThaiClass);
                newTracker.setEventDate(nextEventDate);
                newTracker.setNumberPeopleAttendedClass(0);
                newTracker.setNumberPeopleOnWaitList(0);
                newTracker.setNumberPeopleDidNotAttendClass(0);

                muayThaiClassTrackerRepository.save(newTracker);
            }
        }
    }


    public void getBankHolidayDays() {

        List<BankHolidayDays> bankHolidayDayReposite = bankHolidayRepository.findAll();

        if (!bankHolidayDayReposite.isEmpty()) {

            System.out.println("You have " + bankHolidayDayReposite.size() + " bank holiday days");
        }
        else {

        Set<LocalDate> setBankHolidayDays = new HashSet<>();

        Map<String, BankHolidayResponse> bankHolidayDays = webClient.get()
                .uri("https://www.gov.uk/bank-holidays.json")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, BankHolidayResponse>>() {
                })
                .block();


        if (bankHolidayDays == null) return;

        LocalDate today = LocalDate.now();
        LocalDate endDate = LocalDate.of(2026, 12, 31);


        List<BankHolidayDays> filteredHolidays = bankHolidayDays.values().stream()
                .flatMap(region -> region.getEvents().stream()) // Extract events list and stream them
                .map(event -> LocalDate.parse(event.getDate()))  // Parse the date here once
                .filter(eventDate -> !eventDate.isBefore(today) && !eventDate.isAfter(endDate)) // Filter by date range
                .distinct() // Remove duplicates based on date
                .map(BankHolidayDays::new) // Map LocalDate back to BankHolidayDays
                .collect(Collectors.toList());


        filteredHolidays.removeIf(bankHolidayDay -> !setBankHolidayDays.add(bankHolidayDay.getDate()));


       bankHolidayRepository.saveAll(filteredHolidays);}


    }


    // Automatically runs every Sunday at midnight
    @Scheduled(cron = "0 0 0 * * SUN")
    public void scheduledEventGeneration() {
        generateEvents();
    }
}