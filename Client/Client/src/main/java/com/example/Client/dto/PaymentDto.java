package com.example.Client.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long paymentIdDo;
    private long paymentCardNumberDto;
    private Date paymentExpiredDateDto;
    private int paymentCvvDto;
    private String paymentNameDto;
    private String paymentAddressDto;
    private double paymentAmountDto;

    public Long getPaymentIdDo() {
        return paymentIdDo;
    }

    public void setPaymentIdDo(Long paymentIdDo) {
        this.paymentIdDo = paymentIdDo;
    }

    public long getPaymentCardNumberDto() {
        return paymentCardNumberDto;
    }

    public void setPaymentCardNumberDto(long paymentCardNumberDto) {
        this.paymentCardNumberDto = paymentCardNumberDto;
    }

    public Date getPaymentExpiredDateDto() {
        return paymentExpiredDateDto;
    }

    public void setPaymentExpiredDateDto(Date paymentExpiredDateDto) {
        this.paymentExpiredDateDto = paymentExpiredDateDto;
    }

    public int getPaymentCvvDto() {
        return paymentCvvDto;
    }

    public void setPaymentCvvDto(int paymentCvvDto) {
        this.paymentCvvDto = paymentCvvDto;
    }

    public String getPaymentNameDto() {
        return paymentNameDto;
    }

    public void setPaymentNameDto(String paymentNameDto) {
        this.paymentNameDto = paymentNameDto;
    }

    public String getPaymentAddressDto() {
        return paymentAddressDto;
    }

    public void setPaymentAddressDto(String paymentAddressDto) {
        this.paymentAddressDto = paymentAddressDto;
    }

    public double getPaymentAmountDto() {
        return paymentAmountDto;
    }

    public void setPaymentAmountDto(double paymentAmountDto) {
        this.paymentAmountDto = paymentAmountDto;
    }
}
