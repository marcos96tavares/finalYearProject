package com.example.Admin.controller;


import com.example.Admin.dto.MuayThaiBookingDto;
import com.example.Admin.entity.MuayThaiBooking;
import com.example.Admin.service.MuayThaiBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/booking")
public class MuayThaiBookingController {

    private final MuayThaiBookingService bookingService;

    public MuayThaiBookingController(MuayThaiBookingService bookingService) {
        this.bookingService = bookingService;
    }



    @PostMapping("/book/{studentId}/{trackId}")
    public ResponseEntity<MuayThaiBooking> createBooking(@PathVariable Long studentId, @PathVariable Long trackId) {
        MuayThaiBooking booked = bookingService.createBooking(studentId, trackId);

        return new ResponseEntity<>(booked, HttpStatus.CREATED);
    }

    @GetMapping("/check/{studentId}/{trackerId}")
    public ResponseEntity<Boolean> checkBooking(@PathVariable Long studentId, @PathVariable Long trackerId) {
        boolean isBooked = bookingService.hasUserBookedClass(studentId, trackerId);
        return ResponseEntity.ok(isBooked);
    }

    @GetMapping
    public ResponseEntity<List<MuayThaiBooking>> getAllBookings() {
        List<MuayThaiBooking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }


    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("delete/{studentId}/{trackerId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long studentId, @PathVariable Long trackerId) {

        bookingService.deleteBooking(studentId, trackerId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBookingById(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
