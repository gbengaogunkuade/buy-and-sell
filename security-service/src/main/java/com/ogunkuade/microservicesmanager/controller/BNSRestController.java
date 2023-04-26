package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.dto.BNSProductRequestDto;
import com.ogunkuade.microservicesmanager.dto.BNSProductResponseDto;
import com.ogunkuade.microservicesmanager.dto.BNSProductUpdateRequestDto;
import com.ogunkuade.microservicesmanager.dto.ProductRequestDto;
import com.ogunkuade.microservicesmanager.service.BNSRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/products")
public class BNSRestController {

    private final BNSRestService bnsRestService;

    public BNSRestController(BNSRestService bnsRestService) {
        this.bnsRestService = bnsRestService;
    }


    //CREATE PRODUCT
    @PostMapping("/create")
    public BNSProductResponseDto creatingProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String amount,
            @RequestParam String category,
            @RequestParam String sellerId,
            @RequestParam MultipartFile[] imageList
    ) throws IOException {
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
    ) throws IOException {
        return bnsRestService.updateProduct(name, description, amount, category, id, imageList);
    }


    //GET PRODUCT BY ID


    //GET PRODUCT BY NAME


    //GET PRODUCT BY SELLERID


    //GET ALL PRODUCTS


    //DELETE PRODUCT BY ID









}
