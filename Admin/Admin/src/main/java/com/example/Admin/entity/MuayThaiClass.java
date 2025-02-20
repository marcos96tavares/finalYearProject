package com.example.Admin.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MuayThaiClass {


    /**
     * The unique identifier for a Muay Thai class.
     * This field serves as the primary key for the MuayThaiClass entity
     * and is automatically generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    /**
     * Represents the name of a Muay Thai class.
     * This variable holds the title or descriptive name of the class
     * which is used to uniquely identify or describe the class being offered.
     */
    private String className;
    /**
     * Represents the start time of a Muay Thai class session.
     * This variable holds the specific LocalTime value indicating when a class begins.
     * It is expected to be used for class scheduling and tracking purposes.
     */
    private LocalTime classTimeStart;  // Represents class time (e.g., 14:00)
    /**
     * Represents the ending time of a Muay Thai class session.
     * This variable holds the {@code LocalTime} indicating when the class ends.
     * It is used to schedule and manage the class duration.
     */
    private LocalTime classTimeEnd;
    /**
     * Represents the day(s) of the week when a Muay Thai class is scheduled to occur.
     * This variable references the {@link DayOfWeek} enumeration, which provides constants
     * for the days of the week (e.g., MONDAY, TUESDAY).
     *
     * The field is intended to specify or store the recurring day on which the class session takes place.
     */
    private DayOfWeek weekDays;

    /**
     * Represents the maximum number of participants allowed in a specific Muay Thai class.
     * This variable is used to define the capacity limit for attendees within the class
     * and helps to manage booking and attendance functionality effectively.
     */
    private int classCapacity;

    /**
     * Represents a collection of MuayThaiClassTracker objects associated with a MuayThaiClass entity.
     * This relationship is defined as one-to-many, where a Muay Thai class can have multiple trackers
     * that monitor various aspects of class events, such as attendance, waitlists, and no-shows.
     *
     * Mapped by the "muayThaiClass" field in the MuayThaiClassTracker class, ensuring bidirectional
     * synchronization between the two entities. Any changes to this list will cascade to the associated
     * MuayThaiClassTracker instances, and orphaned trackers will be automatically removed.
     *
     * This field is annotated with @JsonManagedReference to handle proper serialization and resolve
     * potential circular references during JSON conversion.
     */
    @OneToMany(mappedBy = "muayThaiClass", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MuayThaiClassTracker> muayThaiManagers;



}

