package com.example.Admin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MuayThaiBooking {

    /**
     * Identifier for the booking entity in the MuayThaiBooking class.
     * It uniquely represents a specific booking and is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    /**
     * Represents the identifier of a student associated with a booking in the Muay Thai system.
     * This variable holds the unique ID of a student who is part of a Muay Thai booking.
     */
    @Column(nullable = false)
    private Long studentId;

    /**
     * Represents the association between a Muay Thai booking and a Muay Thai class tracker.
     * This relationship is managed through a many-to-one mapping, where multiple bookings
     * are linked to a single class tracker.
     *
     * The MuayThaiClassTracker contains details about a specific class event, such as its
     * attendance and waitlist information. This variable serves as a foreign key reference
     * to establish the connection between a MuayThaiBooking and a MuayThaiClassTracker.
     *
     * The associated table column is `tracker_id`, and it is a non-nullable field, ensuring
     * that every booking must be linked to a valid class tracker entry.
     */
    @ManyToOne
    @JoinColumn(name = "tracker_id", nullable = false)
    private MuayThaiClassTracker muayThaiClassTracker;

}
