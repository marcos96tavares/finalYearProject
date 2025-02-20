package com.example.Admin.dto;

import com.example.Admin.entity.MuayThaiClassTracker;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MuayThaiBookingDto {

    private Long bookingId;
    private Long studentId;
    private MuayThaiClassTracker muayThaiClassTracker;
}
