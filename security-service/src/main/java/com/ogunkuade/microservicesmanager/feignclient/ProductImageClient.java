package com.ogunkuade.microservicesmanager.feignclient;

import com.ogunkuade.microservicesmanager.dto.ImageRequestRecord;
import com.ogunkuade.microservicesmanager.dto.ProductImageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.util.List;


@FeignClient(name = "IMAGES-SERVICE", path = "/images-app/api")
public interface ProductImageClient {


    //POST SINGLE IMAGE
    @PostMapping("/product-image/{id}/upload")
    ProductImageResponseDto imageRestUploading(@RequestBody ImageRequestRecord imageRequestRecord, @PathVariable Long id) throws IOException;


    //GET SINGLE IMAGE
    @GetMapping("/product-image/{id}")
    ProductImageResponseDto gettingRestImage(@PathVariable Long id) throws IOException;


    //GET IMAGE BY PRODUCT ID
    @GetMapping("/product-image/productId/{id}")
    List<ProductImageResponseDto> gettingRestImageByProductId(@PathVariable Long id);


    //GET ALL IMAGES
    @GetMapping("/product-image/all")
    public List<ProductImageResponseDto> gettingAllRestImage();






}




