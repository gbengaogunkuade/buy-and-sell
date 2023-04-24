package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.service.BNSRestService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BNSRestController {

    private final BNSRestService bnsRestService;


    public BNSRestController(BNSRestService bnsRestService) {
        this.bnsRestService = bnsRestService;
    }






}
