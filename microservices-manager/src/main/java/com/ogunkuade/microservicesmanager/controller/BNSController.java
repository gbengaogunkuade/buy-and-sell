package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.dto.BNSProductRequest;
import com.ogunkuade.microservicesmanager.service.BNSService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class BNSController {

    private BNSService bNSService;

    public BNSController(BNSService BNSService) {
        this.bNSService = BNSService;
    }


    @GetMapping("/products/create")
    public String creatingBNS(Model model){
        return bNSService.createBNS(model);
    }


    @PostMapping("/products/create")
    public String creatingBNS_Success(BNSProductRequest bnsProductRequest, @RequestParam MultipartFile[] my_photos) throws IOException {
        return bNSService.createBNS_Success(bnsProductRequest, my_photos);
    }


}
