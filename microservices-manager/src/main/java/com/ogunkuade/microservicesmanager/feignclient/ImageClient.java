package com.ogunkuade.microservicesmanager.feignclient;

import com.ogunkuade.microservicesmanager.dto.ImageResponse;
import com.ogunkuade.microservicesmanager.dto.ProductRequest;
import com.ogunkuade.microservicesmanager.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@FeignClient(name = "IMAGE-SERVICE", path = "/images-app/api")
public interface ImageClient {


    @PostMapping("/{id}/upload")
    List<ImageResponse> imageUploadComplete(@PathVariable Long id, @RequestParam MultipartFile[] my_photos) throws IOException;





}




