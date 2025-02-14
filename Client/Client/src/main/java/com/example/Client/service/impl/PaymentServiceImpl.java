package com.example.Client.service.impl;


import com.example.Client.dto.PaymentDto;
import com.example.Client.entity.Payment;
import com.example.Client.repository.PaymentRepository;
import com.example.Client.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = convertToEntity(paymentDto);
        return convertToDto(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setPaymentCardNumber(paymentDto.getPaymentCardNumberDto());
        payment.setPaymentExpiredDate(paymentDto.getPaymentExpiredDateDto());
        payment.setPaymentCvv(paymentDto.getPaymentCvvDto());
        payment.setPaymentName(paymentDto.getPaymentNameDto());
        payment.setPaymentAddress(paymentDto.getPaymentAddressDto());
        payment.setPaymentAmount(paymentDto.getPaymentAmountDto());

        return convertToDto(paymentRepository.save(payment));
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDto(payment);
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private Payment convertToEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setPaymentCardNumber(dto.getPaymentCardNumberDto());
        payment.setPaymentExpiredDate(dto.getPaymentExpiredDateDto());
        payment.setPaymentCvv(dto.getPaymentCvvDto());
        payment.setPaymentName(dto.getPaymentNameDto());
        payment.setPaymentAddress(dto.getPaymentAddressDto());
        payment.setPaymentAmount(dto.getPaymentAmountDto());
        return payment;
    }

    private PaymentDto convertToDto(Payment payment) {
        return new PaymentDto(
                payment.getPaymentId(),
                payment.getPaymentCardNumber(),
                payment.getPaymentExpiredDate(),
                payment.getPaymentCvv(),
                payment.getPaymentName(),
                payment.getPaymentAddress(),
                payment.getPaymentAmount()
        );
    }

}
