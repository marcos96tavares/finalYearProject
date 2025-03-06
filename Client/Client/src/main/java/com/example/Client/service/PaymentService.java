package com.example.Client.service;

import com.example.Client.dto.PaymentDto;
import com.example.Client.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDto paymentDto);
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    void deletePayment(Long id);
    PaymentDto getPaymentById(Long id);
    List<PaymentDto> getAllPayments();
}
