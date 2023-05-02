package com.ogunkuade.images.controller;


import com.ogunkuade.images.dto.ImageRequest;
import com.ogunkuade.images.dto.ImageRequestRecord;
import com.ogunkuade.images.dto.ProductImageResponse;
import com.ogunkuade.images.service.ProductImageRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductImageRestController {

    private final ProductImageRestService productImageRestService;

    public ProductImageRestController(ProductImageRestService productImageRestService) {
        this.productImageRestService = productImageRestService;
    }



    //POST SINGLE IMAGE
    @PostMapping("/product-image/{id}/upload")
    public ProductImageResponse imageRestUploading(@RequestBody ImageRequest imageRequest, @PathVariable Long id) throws IOException {
        return productImageRestService.imageRestUpload(imageRequest, id);
    }



//    //POST MULTIPLE IMAGE
//    @PostMapping("/product-image/{id}/multiple-upload")
//    public List<ProductImageResponse> imageRestUploadingMultiple(@RequestParam MultipartFile[] imageList, @PathVariable Long id) throws IOException {
//        return productImageRestService.imageRestUploadMultiple(imageList, id);
//    }



    //POST MULTIPLE IMAGE
    @PostMapping("/product-image/{id}/multiple-upload")
    public List<ProductImageResponse> imageRestUploadingMultiple(@RequestBody List<ImageRequest> imageRequestList, @PathVariable Long id) throws IOException {
        return productImageRestService.imageRestUploadMultiple(imageRequestList, id);
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




    //DELETE IMAGE BY ID
    @DeleteMapping("/product-image/{id}/delete")
    public String deletingRestImageById(@PathVariable Long id) throws FileNotFoundException {
        return productImageRestService.deleteRestImageById(id);
    }





}



