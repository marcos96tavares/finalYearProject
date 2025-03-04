package com.example.Client.repository;

import com.example.Client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByFirstName(String username);
    User findByPassword(String username);
    boolean existsByEmail(String email);
}
