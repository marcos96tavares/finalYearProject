package com.example.Admin.repository;

import com.example.Admin.entity.MuayThaiClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MuayThaiClassRepository extends JpaRepository<MuayThaiClass, Long> {


}
