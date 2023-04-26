package com.ogunkuade.microservicesmanager.feignclient;

import com.ogunkuade.microservicesmanager.dto.ProductRequestDto;
import com.ogunkuade.microservicesmanager.dto.ProductResponseDto;
import com.ogunkuade.microservicesmanager.dto.ProductUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;


@FeignClient(name = "PRODUCT-SERVICE", path = "/product-app/api")
public interface ProductClient {


    //CREATE PRODUCT
    @PostMapping("/create")
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequest);


    //UPDATE PRODUCT
    @PutMapping("/{id}/update")
    ProductResponseDto updatingProduct(@RequestBody ProductUpdateRequestDto productUpdateRequestDto, @PathVariable Long id);


    //GET ALL PRODUCTS
    @GetMapping("/all")
    List<ProductResponseDto> gettingAllProducts();


    //GET PRODUCT BY SELLER ID
    @GetMapping("/{id}/sellerId")
    List<ProductResponseDto> gettingProductsBySellerId(@PathVariable Long id) throws FileNotFoundException;


    //GET PRODUCT BY NAME
    @GetMapping("/{name}")
    ProductResponseDto gettingProductByName(@PathVariable String name) throws Exception;


    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    ProductResponseDto gettingProductById(@PathVariable Long id) throws Exception;


    //DELETE PRODUCT BY ID
    @DeleteMapping("/{id}/delete")
    String deletingProductById(@PathVariable Long id) throws Exception;





}




