package com.example.Client.service.impl;

import com.example.Client.entity.VideoResource;
import com.example.Client.repository.VideosRepositor;
import com.example.Client.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private VideosRepositor videosRepositor;

    public VideoServiceImpl(VideosRepositor videosRepositor) {
        this.videosRepositor = videosRepositor;
    }

    @Override
    public List<VideoResource> getAllVedios() {
        return  videosRepositor.findAll();
    }
}
