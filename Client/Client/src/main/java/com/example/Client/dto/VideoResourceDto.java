package com.example.Client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResourceDto {

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
