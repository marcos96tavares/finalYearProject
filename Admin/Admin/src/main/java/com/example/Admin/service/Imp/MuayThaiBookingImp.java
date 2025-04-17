package com.example.Admin.service.Imp;

import com.example.Admin.dto.MuayThaiBookingDto;
import com.example.Admin.dto.MuayThaiClassTrackerDto;
import com.example.Admin.entity.MuayThaiBooking;
import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.MuayThaiClassTracker;
import com.example.Admin.exception.ResourceNotFoundException;
import com.example.Admin.repository.MuayThaiBookRepository;
import com.example.Admin.repository.MuayThaiClassTrackerRepository;
import com.example.Admin.service.MuayThaiBookingService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MuayThaiBookingImp implements MuayThaiBookingService {


  /**
   * Repository for handling data operations related to Muay Thai bookings.
   * This repository is responsible for the persistence, retrieval, update,
   * and deletion of Muay Thai booking records in the database.
   */
  private final MuayThaiBookRepository muayThaiBookingService;
  /**
   * Repository instance for managing persistence and access to MuayThaiClassTracker entities.
   * This is used to perform database operations such as finding, saving, and deleting
   * records related to Muay Thai class tracking in the system.
   */
  private final MuayThaiClassTrackerRepository muayThaiClassTrackerRepository;

  private final MuayThaiClassTrackerServiceImp muayThaiClassTrackerServiceImp;



  /**
   * Constructs an instance of MuayThaiBookingImp which facilitates booking management
   * and class tracker handling for Muay Thai classes.
   *
   * @param muayThaiBookingService1 the repository used for managing Muay Thai bookings
   * @param muayThaiClassTrackerRepository the repository used for managing Muay Thai class trackers
   */
  public MuayThaiBookingImp(MuayThaiBookRepository muayThaiBookingService1, MuayThaiClassTrackerRepository muayThaiClassTrackerRepository, MuayThaiClassTrackerServiceImp muayThaiClassTrackerServiceImp) {
      this.muayThaiBookingService = muayThaiBookingService1;
      this.muayThaiClassTrackerRepository = muayThaiClassTrackerRepository;
      this.muayThaiClassTrackerServiceImp = muayThaiClassTrackerServiceImp;
  }


    /**
     * Creates a new Muay Thai booking based on the provided booking data transfer object (DTO).
     * Retrieves the associated class tracker, maps the booking details, and saves the booking.
     *
    Thai booking,
     *                   including student ID and associated class tracker information.
     * @return the newly created Muay Thai booking after being saved.
     * @throws RuntimeException if the specified class tracker is not found.
     */
    @Override
    public MuayThaiBooking createBooking(Long studentId, Long MuayThaiClassId) {


        MuayThaiClassTracker MuayThaiClassTracker = muayThaiClassTrackerRepository.findById(MuayThaiClassId).get();

        MuayThaiBooking booking = new MuayThaiBooking();
        booking.setStudentId(studentId);
        booking.setMuayThaiClassTracker(MuayThaiClassTracker);

        return muayThaiBookingService.save(booking);
    }


    public boolean deleteAlltheBoolingByStudentId(Long studentId) {
        return muayThaiBookingService.deleteAllByStudentId(studentId);
    }

    /**
     * Retrieves all Muay Thai bookings from the data source.
     *
     * @return a list of MuayThaiBooking objects containing details of all bookings.
     */
    @Override
    public List<MuayThaiBooking> getAllBookings() {
        return muayThaiBookingService.findAll();
    }



    /**
     * Deletes a booking by its unique identifier.
     *
     * @param bookingId the unique identifier of the booking to be deleted
     */
    @Override
    public void deleteBooking(Long bookingId) {
        muayThaiBookingService.deleteById(bookingId);
    }

    @Override
    public List<MuayThaiBookingDto> getBookingsByUserId(Long userId) {

        List<MuayThaiBooking> muayThaiBookings =  muayThaiBookingService.findAllByStudentId(userId);

        return muayThaiBookings.stream()
                .map(this::convertToDto)
                .toList();



    }

    @Override
    public MuayThaiBooking getBooking(Long bookingId) {
        return muayThaiBookingService.findById(bookingId).get();
    }

    @Override
    public boolean hasUserBookedClass(Long studentId, Long MuayThaiClassId) {
        return muayThaiBookingService.existsByStudentIdAndMuayThaiClassTracker_ClassManagerId(studentId, MuayThaiClassId);
    }

    @Override
    public void deleteBooking(Long studendId, Long MuayThaiClassId) {

        MuayThaiBooking muayThaiClass = muayThaiBookingService.findByStudentIdAndMuayThaiClassTracker_ClassManagerId(studendId, MuayThaiClassId);
        muayThaiBookingService.delete(muayThaiClass);
    }


    public  MuayThaiBookingDto convertToDto(MuayThaiBooking booking) {
        if (booking == null) {
            return null;
        }

        MuayThaiBookingDto dto = new MuayThaiBookingDto();
        dto.setStudentId(booking.getStudentId());
        dto.setMuayThaiClassTracker(muayThaiClassTrackerServiceImp.convertToDto(booking.getMuayThaiClassTracker()));
        return dto;
    }

    public  MuayThaiBooking convertToEntity(MuayThaiBookingDto dto) {
        if (dto == null) {
            return null;
        }

        MuayThaiBooking booking = new MuayThaiBooking();
        booking.setBookingId(dto.getBookingId());
        booking.setStudentId(dto.getStudentId());
        booking.setMuayThaiClassTracker(muayThaiClassTrackerServiceImp.convertToEntity(dto.getMuayThaiClassTracker()));
        return booking;
    }



}
