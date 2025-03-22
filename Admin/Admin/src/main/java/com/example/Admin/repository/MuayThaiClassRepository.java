package com.example.Admin.repository;

import com.example.Admin.entity.MuayThaiClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;


@Repository
public interface MuayThaiClassRepository extends JpaRepository<MuayThaiClass, Long> {

    List<MuayThaiClass> findAllByWeekDays(DayOfWeek weekDays);


}
