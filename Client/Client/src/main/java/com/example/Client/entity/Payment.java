package com.example.Client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {

    /**
     * Represents the unique identifier for a payment entity in the database.
     * This field is auto-generated using the identity generation strategy.
     * It serves as the primary key for the Payment entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    /**
     * Represents the card number associated with a payment transaction.
     * Typically a numeric value identifying the credit or debit card
     * used during the payment process.
     */
    private long paymentCardNumber;
    /**
     * Represents the expiration date of the payment card associated with the payment.
     * This field typically indicates the validity of the card and is used to prevent
     * transactions after the card has expired.
     */
    private Date paymentExpiredDate;
    /**
     * Represents the CVV (Card Verification Value) of a payment method.
     * This is a three or four-digit security code printed on the card,
     * typically used for verifying the authenticity of credit or debit card transactions.
     */
    private int paymentCvv;
    /**
     * Represents the name of the payer associated with the payment.
     * This field stores the full name as provided by the payer.
     */
    private String paymentName;
    /**
     * Represents the address associated with the payment.
     * This may include details such as billing address or the address to which the payment is related.
     */
    private String paymentAddress;
    /**
     * Represents the timestamp when the payment details were last updated.
     * This field is automatically updated by the persistence mechanism whenever the
     * corresponding entity is modified.
     *
     * Annotations:
     * - {@code @UpdateTimestamp}: Automatically updates the field with the current timestamp when the entity is updated.
     * - {@code @Temporal(TemporalType.TIMESTAMP)}: Specifies that the field corresponds to a database timestamp type.
     * - {@code @Column(name = "payment_date", nullable = false)}: Maps the field to the "payment_date" column in the database
     *   and ensures it cannot be null.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
    /**
     * Represents the total monetary amount of a payment.
     * This field is used to store the value associated with a specific payment transaction.
     */
    private double paymentAmount;

    @Transient
    public String getFormattedPaymentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
        return formatter.format(paymentDate);
    }

    public void setFormattedPaymentDate(String formattedDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
        this.paymentDate = formatter.parse(formattedDate);
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public long getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public void setPaymentCardNumber(long paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public Date getPaymentExpiredDate() {
        return paymentExpiredDate;
    }

    public void setPaymentExpiredDate(Date paymentExpiredDate) {
        this.paymentExpiredDate = paymentExpiredDate;
    }

    public int getPaymentCvv() {
        return paymentCvv;
    }

    public void setPaymentCvv(int paymentCvv) {
        this.paymentCvv = paymentCvv;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
