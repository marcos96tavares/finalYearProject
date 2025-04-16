package com.example.Client.service;

import com.example.Client.dto.VideoResourceDto;
import com.example.Client.entity.VideoResource;

import java.util.List;

public interface VideoService {


    List<VideoResource> getAllVedios();

    VideoResourceDto createVedio(VideoResourceDto videoResource);

    Boolean deleteVedio(Long videoId);

}
