package com.example.Admin.repository;

import com.example.Admin.entity.MuayThaiBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuayThaiBookRepository extends JpaRepository<MuayThaiBooking, Long> {


}
