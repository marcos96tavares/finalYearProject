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
}
