package com.example.Client.service.impl;


import com.example.Client.dto.PaymentDto;
import com.example.Client.entity.Payment;
import com.example.Client.repository.PaymentRepository;
import com.example.Client.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PaymentServiceImpl implements PaymentService {

    /**
     * Repository interface for performing CRUD operations on Payment entities.
     * Provides database access for managing payment information.
     */
    private final PaymentRepository paymentRepository;

    /**
     * Constructs a new instance of PaymentServiceImpl.
     *
     * @param paymentRepository the PaymentRepository instance used for accessing payment data
     */
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Creates a new payment entry in the system, saving it to the repository.
     *
     * @param paymentDto The data transfer object containing payment details to be created.
     * @return A PaymentDto representing the newly created payment after being saved in the repository.
     */
    @Override
    public Payment createPayment(PaymentDto paymentDto) {
        Payment payment = convertToEntity(paymentDto);
        return paymentRepository.save(payment);
    }

    /**
     * Updates an existing payment with new details.
     *
     * @param id the ID of the payment to be updated
     * @param paymentDto the new payment information to be updated in the record
     * @return the updated payment information as a PaymentDto
     */
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

    /**
     * Deletes a payment record from the repository by its ID.
     *
     * @param id the unique identifier of the payment to be deleted
     */
    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    /**
     * Retrieves a payment by its unique identifier.
     *
     * @param id the unique identifier of the payment
     * @return the payment details as a PaymentDto object
     * @throws RuntimeException if no payment is found with the given identifier
     */
    @Override
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDto(payment);
    }

    /**
     *
     */
    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    /**
     * Converts a PaymentDto object into a Payment entity.
     *
     * @param dto the PaymentDto object containing payment details
     * @return a Payment entity populated with the data from the provided PaymentDto
     */
    public Payment convertToEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setPaymentId(dto.getPaymentIdDo());
        payment.setPaymentCardNumber(dto.getPaymentCardNumberDto());
        payment.setPaymentExpiredDate(setTheExpirationDate());
        payment.setPaymentCvv(dto.getPaymentCvvDto());
        payment.setPaymentName(dto.getPaymentNameDto());
        payment.setPaymentAddress(dto.getPaymentAddressDto());
        payment.setPaymentAmount(dto.getPaymentAmountDto());

        return payment;
    }

    /**
     * Converts a Payment entity to a PaymentDto object.
     *
     * @param payment the Payment entity to be converted
     * @return the converted PaymentDto object
     */
    public PaymentDto convertToDto(Payment payment) {
        return new PaymentDto(
                payment.getPaymentId(),
                payment.getPaymentCardNumber(),
                payment.getPaymentExpiredDate(),
                payment.getPaymentExpiryDateDtoString(),
                payment.getPaymentCvv(),
                payment.getPaymentName(),
                payment.getPaymentAddress(),
                payment.getPaymentAmount()
        );
    }



    private Date setTheExpirationDate() {
        Date expirationDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expirationDate);
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        return calendar.getTime();
    }

}
