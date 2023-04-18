package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.service.BNSService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BNSController {

    private final BNSService bnsService;

    public BNSController(BNSService bnsService) {
        this.bnsService = bnsService;
    }


    @GetMapping({"/home", "/"})
    public String gettingHome(){
        return bnsService.getHome();
    }



}



