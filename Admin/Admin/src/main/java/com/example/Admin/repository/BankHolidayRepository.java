package com.example.Admin.repository;

import com.example.Admin.entity.BankHolidayDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankHolidayRepository extends JpaRepository<BankHolidayDays, Long> {
}
