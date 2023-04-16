package com.ogunkuade.productservice.controller;


import com.ogunkuade.productservice.dto.ProductRequest;
import com.ogunkuade.productservice.dto.ProductResponse;
import com.ogunkuade.productservice.entity.Product;
import com.ogunkuade.productservice.service.ProductRestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductRestService productRestService;

    public ProductRestController(ProductRestService productRestService) {
        this.productRestService = productRestService;
    }



    //CREATE PRODUCT
    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productRestService.createProduct(productRequest);
    }




    //UPDATE PRODUCT


    //GET ALL PRODUCTS


    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product gettingProductById(@PathVariable Long id){
        return productRestService.getProductById(id);
    }

    //GET PRODUCT BY SELLER


    //GET PRODUCT BY NAME


    //DELETE PRODUCT BY NAME


}
