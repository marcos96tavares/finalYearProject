package com.example.Admin.service;

import com.example.Admin.dto.MuayThaiBookingDto;
import com.example.Admin.entity.MuayThaiBooking;

import java.util.List;

public interface MuayThaiBookingService {


    MuayThaiBooking createBooking(MuayThaiBookingDto bookingDto);


    List<MuayThaiBooking> getAllBookings();









    void deleteBooking(Long bookingId);

}
