package com.example.Client.dto;

import com.example.Client.entity.Payment;
import com.example.Client.entity.Status;
import com.example.Client.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MembershipDto {


    private Long membershipId;

    private UserDto userId;

    private PaymentDto paymentId;


    private Status paymentStatus;



}
