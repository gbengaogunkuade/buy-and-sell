package com.ogunkuade.productservice.controller;


import com.ogunkuade.productservice.dto.ProductRequest;
import com.ogunkuade.productservice.dto.ProductResponse;
import com.ogunkuade.productservice.entity.Product;
import com.ogunkuade.productservice.repository.ProductRepository;
import com.ogunkuade.productservice.service.ProductRestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductRestService productRestService;
    private final ProductRepository productRepository;

    public ProductRestController(ProductRestService productRestService, ProductRepository productRepository) {
        this.productRestService = productRestService;
        this.productRepository = productRepository;
    }



    //CREATE PRODUCT
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse creatingProduct(@RequestBody ProductRequest productRequest){
        return productRestService.createProduct(productRequest);
    }




    //UPDATE PRODUCT


    //GET ALL PRODUCTS
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> gettingAllProducts(){
        return productRestService.getAllProducts();
    }




    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product gettingProductById(@PathVariable Long id){
        return productRestService.getProductById(id);
    }


    
    //GET PRODUCT BY SELLER
    @GetMapping("/{id}/sellerId")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> gettingProductsBySellerId(@PathVariable Long id) throws FileNotFoundException {
        return productRestService.getProductsBySellerId(id);
    }



    //GET PRODUCT BY NAME




    //DELETE PRODUCT BY NAME


}
