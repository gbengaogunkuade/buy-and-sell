package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.dto.BNSProductRequest;
import com.ogunkuade.microservicesmanager.service.BNSProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class BNSProductController {

    private BNSProductService bNSProductService;

    public BNSProductController(BNSProductService BNSProductService) {
        this.bNSProductService = BNSProductService;
    }


    @GetMapping("/products/create")
    public String creatingBNS(Model model){
        return bNSProductService.createBNS(model);
    }


    @PostMapping("/products/create")
    public String creatingBNS_Success(BNSProductRequest bnsProductRequest, @RequestParam MultipartFile[] my_photos) throws IOException {
        return bNSProductService.createBNS_Success(bnsProductRequest, my_photos);
    }


}
