package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.ImageRequestRecord;
import com.ogunkuade.images.dto.ProductImageResponse;
import com.ogunkuade.images.service.ProductImageRestService;
import org.springframework.web.bind.annotation.*;

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
    public ProductImageResponse imageRestUploading(@RequestBody ImageRequestRecord imageRequestRecord, @PathVariable Long id) throws IOException {
        return productImageRestService.imageRestUpload(imageRequestRecord, id);
    }



    //GET SINGLE IMAGE
    @GetMapping("/product-image/{id}")
    public ProductImageResponse gettingRestImage(@PathVariable Long id) throws IOException {
        return productImageRestService.getRestImage(id);
    }


    //GET IMAGE BY PRODUCT ID
    @GetMapping("/product-image/productId/{id}")
    public List<ProductImageResponse> gettingRestImageByProductId(@PathVariable Long id) throws IOException {
        return productImageRestService.getRestImageByProductId(id);
    }


    //GET ALL IMAGES
    @GetMapping("/product-image/all")
    public List<ProductImageResponse> gettingAllRestImage() {
        return productImageRestService.getAllRestImage();
    }




}



