package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.ImageRequestRecord;
import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.service.UserImageRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
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
    @ResponseStatus(HttpStatus.OK)
    public UserImageResponse imageRestUploading(@RequestBody ImageRequestRecord imageRequestRecord, @PathVariable Long id) throws IOException {
        return userImageRestService.imageRestUpload(imageRequestRecord, id);
    }


    //GET SINGLE IMAGE BY ID
    @GetMapping("/user-image/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserImageResponse gettingRestImage(@PathVariable Long id) throws IOException {
        return userImageRestService.getRestImage(id);
    }


    //GET SINGLE IMAGE BY USER ID
    @GetMapping("/user-image/userId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserImageResponse gettingRestImageByUserId(@PathVariable Long id) throws IOException {
        return userImageRestService.getRestImageByUserId(id);
    }



    //GET ALL IMAGES
    @GetMapping("/user-image/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserImageResponse> gettingAllRestImage() {
        return userImageRestService.getAllRestImage();
    }


    //GET A SAMPLE IMAGE FOR TESTING
    @GetMapping("/user-image/sample/{id}")
    @ResponseStatus(HttpStatus.OK)
    public byte[] gettingSampleImage(@PathVariable Long id) throws FileNotFoundException {
        return userImageRestService.getSampleImage(id);
    }



}



