package com.example.Client.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Duration;
import java.util.Timer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class VideoResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;
    private String videoTitle;
    private String videoUrl;





}
