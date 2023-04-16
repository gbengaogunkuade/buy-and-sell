package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.ImageResponse;
import com.ogunkuade.images.service.ImageRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ImageRestController {

    private ImageRestService imageRestService;

    public ImageRestController(ImageRestService imageRestService) {
        this.imageRestService = imageRestService;
    }


    //POST MULTIPLE IMAGES
    @PostMapping("/{id}/upload")
    public List<ImageResponse> imageUploadComplete(@PathVariable Long id, @RequestParam MultipartFile[] my_photos) throws IOException {
        return imageRestService.imageUploadRestComplete(id, my_photos);
    }


    //GET SINGLE IMAGE
    @GetMapping("/{id}")
    public ImageResponse gettingRestImage(@PathVariable Long id) throws IOException {
        return imageRestService.getRestImage(id);
    }


    //GET ALL IMAGES
    @GetMapping("/all")
    public List<ImageResponse> gettingAllRestImage() {
        return imageRestService.getAllRestImage();
    }



}



