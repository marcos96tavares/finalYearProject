package com.example.Admin.service.Imp;


import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.MuayThaiClassTracker;
import com.example.Admin.repository.MuayThaiClassRepository;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

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

    /**
     * Constructs an EventGenerationService.
     *
     * @param muayThaiClassRepository the repository used to manage Muay Thai class entities
     * @param muayThaiClassTrackerRepository the repository used to manage Muay Thai class tracker entities
     */
    public EventGenerationService(MuayThaiClassRepository muayThaiClassRepository, MuayThaiClassTrackerRepository muayThaiClassTrackerRepository) {
        this.muayThaiClassRepository = muayThaiClassRepository;
        this.muayThaiClassTrackerRepository = muayThaiClassTrackerRepository;
    }

    /**
     * Generates tracker records for scheduled Muay Thai classes for the upcoming week.
     *
     * This method retrieves all existing Muay Thai classes from the database and computes
     * the next event date based on the specified weekday for each class. It checks whether
     * a tracker entry already exists for each class on the calculated date in the next week.
     * If no tracker exists for that date, a new tracker is created and stored in the database
     * with initial attendance statistics set to zero.
     *
     * The method ensures that new tracker entries are only created for classes that do not
     * already have existing trackers for the specified week.
     *
     * Transactional behavior is applied to ensure that database operations are executed atomically.
     */
    @Transactional
    public void generateEvents() {

        List<MuayThaiClass> allClasses = muayThaiClassRepository.findAll();

        // Get next week's Monday as a reference point
        LocalDate nextWeekStart = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);

        for (MuayThaiClass muayThaiClass : allClasses) {
            // Determine next week's event date for this class
            LocalDate nextEventDate = nextWeekStart.with(muayThaiClass.getWeekDays());

            // Check if this class already has a tracker for next week
            boolean exists = muayThaiClassTrackerRepository.existsByMuayThaiClassAndEventDate(muayThaiClass, nextEventDate);
            if (!exists) {
                // Create a new tracker for the next week
                MuayThaiClassTracker newTracker = new MuayThaiClassTracker();
                newTracker.setMuayThaiClass(muayThaiClass);
                newTracker.setEventDate(nextEventDate);
                newTracker.setNumberPeopleAttendedClass(0);
                newTracker.setNumberPeopleOnWaitList(0);
                newTracker.setNumberPeopleDidNotAttendClass(0);

                // Save to database
                muayThaiClassTrackerRepository.save(newTracker);
            }
        }
    }
}