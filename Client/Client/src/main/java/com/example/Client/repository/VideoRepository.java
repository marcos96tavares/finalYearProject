package com.example.Client.repository;

import com.example.Client.entity.VideoResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<VideoResource,Long> {


}
