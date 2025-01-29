package com.example.Client.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


enum  Status{
    PAID,
    NOT_PAID,
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;
    private final int FEE = 120;

    @OneToOne
    private User userId;
    @OneToOne
    private Payment paymentId;
}
