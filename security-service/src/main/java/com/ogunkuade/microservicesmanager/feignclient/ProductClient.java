package com.ogunkuade.microservicesmanager.feignclient;

import com.ogunkuade.microservicesmanager.dto.ProductRequest;
import com.ogunkuade.microservicesmanager.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "PRODUCT-SERVICE", path = "/product-app/api")
public interface ProductClient {


    @PostMapping("/create")
    ProductResponse createProduct(@RequestBody ProductRequest productRequest);




}




