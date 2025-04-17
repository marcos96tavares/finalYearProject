package com.example.Client.entity;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Membership {

    /**
     * Represents the unique identifier for a Membership entity.
     *
     * This field is annotated with @Id to indicate it as the primary key
     * and @GeneratedValue with strategy GenerationType.IDENTITY to
     * auto-generate its value.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;
    /**
     * Represents the association between a membership and a user.
     * This field establishes a one-to-one relationship with the {@link User} entity,
     * identifying the user associated with the specific membership.
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    private User userId;
    /**
     * Represents the payment information associated with a membership.
     * This field establishes a one-to-one relationship between Membership
     * and Payment entities, linking a Membership record to a specific Payment.
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    private Payment paymentId;

    /**
     * Represents the payment status associated with the membership.
     * Payment status indicates whether the payment for the membership
     * has been made or not.
     * Possible values are defined in the {@link Status} enum, such as PAID and NOT_PAID.
     * This field is stored as a string in the database through the {@code EnumType.STRING} mapping.
     */
    @Enumerated(EnumType.STRING)
    private Status paymentStatus;


}
