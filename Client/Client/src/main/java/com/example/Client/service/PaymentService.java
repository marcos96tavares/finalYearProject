package com.example.Client.service;

import com.example.Client.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    void deletePayment(Long id);
    PaymentDto getPaymentById(Long id);
    List<PaymentDto> getAllPayments();
}
