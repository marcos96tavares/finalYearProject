package com.example.Client.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {

    private Long paymentIdDo;
    private String paymentCardNumberDto;
    private Date paymentExpiredDateDto;
    private String paymentExpiryDateDtoString;
    private int paymentCvvDto;
    private String paymentNameDto;
    private String paymentAddressDto;
    private double paymentAmountDto;



}
