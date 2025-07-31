package com.example.Client.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "[user]")
public class User {

    /**
     * Represents the unique identifier for a user entity in the database.
     * This field is auto-generated using the identity generation strategy.
     * It serves as the primary key for the User entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    /**
     * Represents the first name of the user.
     * This field stores the given name of the user as a String.
     */
    private String firstName;
    /**
     * Represents the last name of a user.
     * This field stores the family name or surname of the user as a string.
     */
    private String lastName;
    /**
     * Represents the email address of the user.
     * This field is used to store the unique email identifier
     * associated with a specific user.
     */
    private String email;

    private String password;

    @Column(name = "age", nullable = true)
    private Integer age;






}
