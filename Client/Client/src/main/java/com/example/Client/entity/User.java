package com.example.Client.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
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


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
