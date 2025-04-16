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

    /**
     * Represents the unique identifier for a video resource in the database.
     * This field is automatically generated using the identity generation strategy.
     * It serves as the primary key for the VideoResource entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;
    /**
     * Represents the title of a video resource.
     * This field stores a descriptive or identifying name for the video,
     * typically used for display or cataloging purposes.
     */
    private String videoTitle;
    /**
     * Represents the URL of the video resource.
     * This field stores the location of the video as a String,
     * typically in the form of a web link or a file path.
     */
    private String videoUrl;


}
