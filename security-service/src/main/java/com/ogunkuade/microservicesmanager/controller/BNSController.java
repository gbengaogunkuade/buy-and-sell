package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.service.BNSService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/gui")
public class BNSController {

    private final BNSService bnsService;


    public BNSController(BNSService bnsService) {
        this.bnsService = bnsService;
    }


    @GetMapping({"/home", "/"})
    public String gettingHome(){
        return bnsService.getHome();
    }


    @GetMapping("/kobo")
    public String gettingIndexPage(Model model) throws Exception {
        return bnsService.getIndexPage(model);
    }



    @GetMapping("/{id}")
    public String getSingleProductPage(@PathVariable Long id, Model model) throws Exception {
        return bnsService.getSingleProductPage(id, model);
    }



}



