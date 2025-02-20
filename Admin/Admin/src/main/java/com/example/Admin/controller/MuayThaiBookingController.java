package com.example.Admin.controller;


import com.example.Admin.dto.MuayThaiBookingDto;
import com.example.Admin.entity.MuayThaiBooking;
import com.example.Admin.service.MuayThaiBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/muay-thai/bookings")
public class MuayThaiBookingController {

    private final MuayThaiBookingService bookingService;

    public MuayThaiBookingController(MuayThaiBookingService bookingService) {
        this.bookingService = bookingService;
    }



    @PostMapping
    public ResponseEntity<MuayThaiBooking> createBooking(@RequestBody MuayThaiBookingDto bookingDto) {
        MuayThaiBooking newBooking = bookingService.createBooking(bookingDto);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
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
}
