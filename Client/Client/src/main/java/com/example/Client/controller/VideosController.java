package com.example.Client.controller;


import com.example.Client.dto.VideoResourceDto;
import com.example.Client.entity.VideoResource;
import com.example.Client.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/video")
public class VideosController {

private final VideoService videoService;

    public VideosController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<VideoResource>> allvideoResource(){

        List<VideoResource> listVideos = videoService.getAllVedios();
        return new ResponseEntity<>(listVideos, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<VideoResourceDto> createVideoResource(@RequestBody VideoResourceDto videoResource) {

        videoService.createVedio(videoResource);
        return new ResponseEntity<>(videoResource, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteVideoResource(@PathVariable("id") Long videoId) {
       var value = videoService.deleteVedio(videoId);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

}
