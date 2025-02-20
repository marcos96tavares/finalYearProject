package com.example.Admin.repository;

import com.example.Admin.entity.MuayThaiClass;
import com.example.Admin.entity.MuayThaiClassTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MuayThaiClassTrackerRepository extends JpaRepository<MuayThaiClassTracker, Long> {

    boolean existsByMuayThaiClassAndEventDate(MuayThaiClass muayThaiClass, LocalDate nextEventDate);
}
