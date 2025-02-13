package com.example.Client.repository;

import com.example.Client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
