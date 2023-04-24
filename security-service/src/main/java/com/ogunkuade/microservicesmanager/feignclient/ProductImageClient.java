package com.ogunkuade.microservicesmanager.feignclient;

import com.ogunkuade.microservicesmanager.dto.ProductImageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;


@FeignClient(name = "IMAGES-SERVICE", path = "/images-app/api")
public interface ProductImageClient {


    //POST MULTIPLE IMAGES
    @PostMapping("/product-image/{id}/upload")
    ProductImageResponseDto imageRestUploading(@RequestBody List<byte[]> imageList, @PathVariable Long id) throws IOException;




}




