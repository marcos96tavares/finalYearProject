package com.example.Admin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MuayThaiClassTracker {

    /**
     * Represents the unique identifier for a class manager in the context of
     * Muay Thai class tracking. This field serves as the primary key for the
     * MuayThaiClassTracker entity and is automatically generated using the
     * IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classManagerId;
    /**
     * Represents the number of people who attended a specific Muay Thai class session.
     *
     * This variable tracks the attendance count for a given class event, enabling
     * management and analysis of participation levels.
     */
    private int numberPeopleAttendedClass;
    /**
     * Represents the number of people on the waitlist for a specific Muay Thai class event.
     *
     * This variable tracks the count of individuals who are currently on the waitlist,
     * indicating their interest in attending the class but were unable to secure a confirmed booking
     * due to capacity restrictions or other circumstances. The value is updated as waitlisted participants
     * are added or removed.
     */
    private int numberPeopleOnWaitList;
    /**
     * Represents the number of people who registered for a class but did not attend it.
     * This variable is used to track and store the count of no-shows for a specific
     * Muay Thai class event in the system.
     *
     * It helps in analyzing attendance trends and improving class scheduling or
     * management strategies by identifying attendance discrepancies.
     */
    private int numberPeopleDidNotAttendClass;// Corrected naming
    /**
     * Represents the date on which the Muay Thai class or event occurred.
     * This variable stores the {@code LocalDate} value corresponding to the
     * specific date of the class session being tracked.
     *
     * It is used to link the tracker information, such as attendance, waitlist,
     * and no-show counts, to a particular date.
     */
    private LocalDate eventDate;



    /**
     * Represents a many-to-one relationship between the MuayThaiClassTracker entity
     * and the MuayThaiClass entity. This field denotes the association of a specific
     * Muay Thai class tracker with its corresponding Muay Thai class.
     *
     * The relationship is established via a foreign key (muay_thai_class_id), which
     * links to the primary key of the MuayThaiClass entity. It ensures that each
     * tracker is mapped to exactly one Muay Thai class, while a single class can
     * have multiple trackers associated with it.
     *
     * Annotated with @JsonBackReference to handle JSON serialization, avoiding
     * circular references by breaking the back-reference in the JSON output.
     * The joining column is defined as non-nullable to ensure that every
     * MuayThaiClassTracker instance is linked to a valid MuayThaiClass object.
     */
    @ManyToOne
    @JoinColumn(name = "muay_thai_class_id", nullable = false)  // Explicit FK name
    @JsonBackReference
    private MuayThaiClass muayThaiClass;

    /**
     * Represents the list of bookings associated with a specific Muay Thai class tracker.
     *
     * This variable holds a collection of {@code MuayThaiBooking} entities that are linked
     * to the {@code MuayThaiClassTracker}. It indicates the bookings made for a specific
     * Muay Thai class event, where each booking is associated with a participant or student.
     *
     * The relationship is established as a one-to-many association, where a single
     * {@code MuayThaiClassTracker} can have multiple {@code MuayThaiBooking} records.
     * The bookings are managed via cascading operations, and orphaned bookings are
     * automatically removed from the database when they are disassociated
     * from the class tracker.
     *
     * The "mappedBy" attribute refers to the {@code muayThaiClassTracker} field in the
     * {@code MuayThaiBooking} class, ensuring bidirectional mapping between the entities.
     */
    @OneToMany(mappedBy = "muayThaiClassTracker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MuayThaiBooking> bookings;

}
