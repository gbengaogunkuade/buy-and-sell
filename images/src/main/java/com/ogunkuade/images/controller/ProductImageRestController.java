package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.ProductImageResponse;
import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.service.ProductImageRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductImageRestController {

    private ProductImageRestService productImageRestService;

    public ProductImageRestController(ProductImageRestService productImageRestService) {
        this.productImageRestService = productImageRestService;
    }


    //POST MULTIPLE IMAGES
    @PostMapping("/product-image/{id}/upload")
    public List<ProductImageResponse> imageUploadComplete(@PathVariable Long id, @RequestParam MultipartFile[] my_photos) throws IOException {
        return productImageRestService.imageUploadRestComplete(id, my_photos);
    }


    //GET SINGLE IMAGE
    @GetMapping("/product-image/{id}")
    public ProductImageResponse gettingRestImage(@PathVariable Long id) throws IOException {
        return productImageRestService.getRestImage(id);
    }


    //GET ALL IMAGES
    @GetMapping("/product-image/all")
    public List<ProductImageResponse> gettingAllRestImage() {
        return productImageRestService.getAllRestImage();
    }



}



