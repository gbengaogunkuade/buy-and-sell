package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.service.UserImageRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserImageRestController {

    private final UserImageRestService userImageRestService;

    public UserImageRestController(UserImageRestService userImageRestService) {
        this.userImageRestService = userImageRestService;
    }


    //POST SINGLE IMAGE
    @PostMapping("/user-image/{id}/upload")
    public UserImageResponse imageRestUploading(@RequestBody byte[] image, @PathVariable Long id) throws IOException {
        return userImageRestService.imageRestUpload(image, id);
    }


    //GET SINGLE IMAGE
    @GetMapping("/user-image/{id}")
    public UserImageResponse gettingRestImage(@PathVariable Long id) throws IOException {
        return userImageRestService.getRestImage(id);
    }


    //GET ALL IMAGES
    @GetMapping("/user-image/all")
    public List<UserImageResponse> gettingAllRestImage() {
        return userImageRestService.getAllRestImage();
    }



}



