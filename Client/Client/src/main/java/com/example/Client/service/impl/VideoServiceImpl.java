package com.example.Client.service.impl;

import com.example.Client.dto.VideoResourceDto;
import com.example.Client.entity.VideoResource;
import com.example.Client.repository.VideoRepository;
import com.example.Client.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    /**
     * An instance of {@link VideoRepository} used to interact with the database
     * for performing CRUD operations on video resources.
     */
    private VideoRepository videosRepositor;

    /**
     * Constructs a new instance of VideoServiceImpl with the specified VideoRepository.
     *
     * @param videosRepositor the repository used to perform data operations for video resources
     */
    public VideoServiceImpl(VideoRepository videosRepositor) {
        this.videosRepositor = videosRepositor;
    }

    /**
     * Retrieves a list of all video resources available in the system.
     *
     * @return a list of {@link VideoResource} objects representing all video resources.
     */
    @Override
    public List<VideoResource> getAllVedios() {
        return  videosRepositor.findAll();
    }

    @Override
    public VideoResourceDto createVedio(VideoResourceDto videoResource) {


        VideoResource video = new VideoResource();
        video.setVideoTitle(videoResource.getVideoTitle());
        video.setVideoUrl(videoResource.getVideoUrl());
        videosRepositor.save(video);
        return videoResource;
    }

    @Override
    public Boolean deleteVedio(Long videoId) {
        videosRepositor.deleteById(videoId);
        return true;
    }




}
