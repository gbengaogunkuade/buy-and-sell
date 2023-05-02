package com.ogunkuade.microservicesmanager.feignclient;


import com.ogunkuade.microservicesmanager.dto.ImageRequest;
import com.ogunkuade.microservicesmanager.dto.ImageRequestRecord;
import com.ogunkuade.microservicesmanager.dto.ProductImageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@FeignClient(name = "IMAGES-SERVICE", path = "/images-app/api")
public interface ImageClient {


//PRODUCT IMAGE

    //POST SINGLE IMAGE
    @PostMapping("/product-image/{id}/upload")
    ProductImageResponseDto imageRestUploading(@RequestBody ImageRequestRecord imageRequestRecord, @PathVariable Long id);


    //POST MULTIPLE IMAGE
    @PostMapping("/product-image/{id}/multiple-upload")
    List<ProductImageResponseDto> imageRestUploadingMultiple(@RequestBody List<ImageRequest> imageRequestList, @PathVariable Long id);


    //GET SINGLE IMAGE
    @GetMapping("/product-image/{id}")
    ProductImageResponseDto gettingRestImage(@PathVariable Long id) throws IOException;


    //GET IMAGE BY PRODUCT ID
    @GetMapping("/product-image/productId/{id}")
    List<ProductImageResponseDto> gettingRestImageByProductId(@PathVariable Long id);


    //GET ALL IMAGES
    @GetMapping("/product-image/all")
    List<ProductImageResponseDto> gettingAllRestImage();



    @DeleteMapping("/product-image/{id}/delete")
    String deletingRestImageById(@PathVariable Long id);



//USER IMAGE


//    //POST SINGLE IMAGE
//    @PostMapping("/user-image/{id}/upload")
//    UserImageResponseDto imageRestUploading(@RequestBody ImageRequestRecord imageRequestRecord, @PathVariable Long id) throws IOException;
//
//
//    //GET SINGLE IMAGE BY ID
//    @GetMapping("/user-image/{id}")
//    UserImageResponseDto gettingRestImage(@PathVariable Long id) throws IOException;
//
//
//    //GET SINGLE IMAGE BY USER ID
//    @GetMapping("/user-image/userId/{id}")
//    UserImageResponseDto gettingRestImageByUserId(@PathVariable Long id) throws IOException;
//
//
//    //GET ALL IMAGES
//    @GetMapping("/user-image/all")
//    List<UserImageResponseDto> gettingAllRestImage();
//








}
