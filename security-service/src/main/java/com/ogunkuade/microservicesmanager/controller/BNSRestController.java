package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.dto.BNSProductResponseDto;
import com.ogunkuade.microservicesmanager.service.BNSRestService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/products")
@Validated
public class BNSRestController {

    private final BNSRestService bnsRestService;

    public BNSRestController(BNSRestService bnsRestService) {
        this.bnsRestService = bnsRestService;
    }



    //CREATE PRODUCT
    @PostMapping("/create")
    public BNSProductResponseDto creatingProduct(
            @RequestParam @NotBlank(message = "NAME FIELD CAN NOT BE BLANK") String name,
            @RequestParam String description,
            @RequestParam String amount,
            @RequestParam String category,
            @RequestParam @Pattern(regexp = "^[1-9]+$", message = "SELLERID MUST BE NUMBER ONLY") String sellerId,
            @RequestParam MultipartFile[] imageList
    ) throws Exception {
        return bnsRestService.createProduct(name, description, amount, category, sellerId, imageList);
    }




    //UPDATE PRODUCT
    @PutMapping("/{id}/update")
    public BNSProductResponseDto updatingProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String amount,
            @RequestParam String category,
            @RequestParam String id,
            @RequestParam MultipartFile[] imageList
    ) throws Exception {
        return bnsRestService.updateProduct(name, description, amount, category, id, imageList);
    }



    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    public BNSProductResponseDto gettingProductById(@PathVariable Long id) throws Exception {
        return bnsRestService.getProductById(id);
    }



    //GET ALL PRODUCTS
    @GetMapping("/all")
    public List<BNSProductResponseDto> gettingAllProducts() throws Exception {
        return bnsRestService.getAllProducts();
    }




    //GET PRODUCT BY NAME
    @GetMapping("/name/{name}")
    public BNSProductResponseDto gettingProductByName(@PathVariable String name) throws Exception {
        return bnsRestService.getProductByName(name);
    }




    //GET PRODUCTS BY SELLERID
    @GetMapping("/seller/{id}")
    public List<BNSProductResponseDto> gettingProductsBySellerId(@PathVariable Long id) throws Exception {
        return bnsRestService.getProductBySellerId(id);
    }




    //DELETE PRODUCT BY ID
    @DeleteMapping("/{id}/delete")
    public String deletingProductById(@PathVariable Long id) throws Exception {
        return bnsRestService.deleteProductById(id);
    }









}
