package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.ImageRequest;
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
    public UserImageResponse userImageRestUploading(@RequestBody ImageRequest imageRequest, @PathVariable Long id) throws IOException {
        return userImageRestService.userImageRestUpload(imageRequest, id);
    }


    //GET SINGLE IMAGE BY ID
    @GetMapping("/user-image/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserImageResponse gettingUserRestImage(@PathVariable Long id) throws IOException {
        return userImageRestService.getUserRestImage(id);
    }


    //GET SINGLE IMAGE BY USER ID
    @GetMapping("/user-image/userId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserImageResponse gettingUserRestImageByUserId(@PathVariable Long id) throws IOException {
        return userImageRestService.getUserRestImageByUserId(id);
    }



    //GET ALL IMAGES
    @GetMapping("/user-image/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserImageResponse> gettingAllUserRestImage() {
        return userImageRestService.getAllUserRestImage();
    }



    //GET A SAMPLE IMAGE FOR TESTING
    @GetMapping("/user-image/sample/{id}")
    @ResponseStatus(HttpStatus.OK)
    public byte[] gettingSampleUserImage(@PathVariable Long id) throws FileNotFoundException {
        return userImageRestService.getSampleImage(id);
    }



}



