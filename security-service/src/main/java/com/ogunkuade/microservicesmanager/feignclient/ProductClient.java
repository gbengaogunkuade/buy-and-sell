package com.ogunkuade.microservicesmanager.feignclient;

import com.ogunkuade.microservicesmanager.dto.ProductRequestDto;
import com.ogunkuade.microservicesmanager.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "PRODUCT-SERVICE", path = "/product-app/api")
public interface ProductClient {


    @PostMapping("/create")
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequest);




}




