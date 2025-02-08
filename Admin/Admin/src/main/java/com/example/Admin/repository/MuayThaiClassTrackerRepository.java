package com.example.Admin.repository;

import com.example.Admin.entity.MuayThaiClassTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuayThaiClassTrackerRepository extends JpaRepository<MuayThaiClassTracker, Long> {
}
