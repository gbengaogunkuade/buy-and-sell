package com.ogunkuade.microservicesmanager.feignclient;


import com.ogunkuade.microservicesmanager.dto.ImageRequestRecord;
import com.ogunkuade.microservicesmanager.dto.UserImageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@FeignClient(name = "IMAGES-SERVICE", path = "/images-app/api")
public interface UserImageClient {


    //POST SINGLE IMAGE
    @PostMapping("/user-image/{id}/upload")
    UserImageResponseDto imageRestUploading(@RequestBody ImageRequestRecord imageRequestRecord, @PathVariable Long id) throws IOException;


    //GET SINGLE IMAGE BY ID
    @GetMapping("/user-image/{id}")
    UserImageResponseDto gettingRestImage(@PathVariable Long id) throws IOException;


    //GET SINGLE IMAGE BY USER ID
    @GetMapping("/user-image/userId/{id}")
    UserImageResponseDto gettingRestImageByUserId(@PathVariable Long id) throws IOException;


    //GET ALL IMAGES
    @GetMapping("/user-image/all")
    List<UserImageResponseDto> gettingAllRestImage();



}
