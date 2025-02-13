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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private long paymentCardNumber;
    private Date paymentExpiredDate;
    private int paymentCvv;
    private String paymentName;
    private String paymentAddress;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
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
