package com.anime.weebhaven.weebhaven;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/video")
public class VideoController {

    private static final String VIDEO_PATH = "src/main/resources/videos/stockVideo.mp4";

    @GetMapping
    public ResponseEntity<Resource> streamVideo() throws IOException {
        File videoFile = new File(VIDEO_PATH);
        Path path = Paths.get(videoFile.getAbsolutePath());
        Resource videoResource = new FileSystemResource(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(videoFile.length());
        headers.setContentDispositionFormData("attachment", videoFile.getName());

        return new ResponseEntity<>(videoResource, headers, HttpStatus.OK);
    }
}
