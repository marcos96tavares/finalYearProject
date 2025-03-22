package com.example.Admin.service;

import com.example.Admin.dto.MuayThaiBookingDto;
import com.example.Admin.entity.MuayThaiBooking;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.List;

public interface MuayThaiBookingService {


    MuayThaiBooking createBooking(Long StudentId, Long MuayThaiClassId);


    List<MuayThaiBooking> getAllBookings();


    void deleteBooking(Long bookingId);

    List<MuayThaiBookingDto> getBookingsByUserId(Long userId);

    MuayThaiBooking getBooking(Long bookingId);

    boolean hasUserBookedClass(Long studentId, Long MuayThaiClassId);

    void deleteBooking(Long studendId, Long MuayThaiClassId);


}
