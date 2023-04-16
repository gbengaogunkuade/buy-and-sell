package com.ogunkuade.microservicesmanager.service;


import com.ogunkuade.microservicesmanager.dto.BNSProductRequest;
import com.ogunkuade.microservicesmanager.dto.ProductRequest;
import com.ogunkuade.microservicesmanager.feignclient.ImageClient;
import com.ogunkuade.microservicesmanager.feignclient.ProductClient;
import com.ogunkuade.microservicesmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class BNSService {

    Logger logger = LoggerFactory.getLogger(BNSService.class);

    private UserRepository userRepository;
    private ProductClient productClient;
    private ImageClient imageClient;

    public BNSService(UserRepository userRepository, ProductClient productClient, ImageClient imageClient) {
        this.userRepository = userRepository;
        this.productClient = productClient;
        this.imageClient = imageClient;
    }




    public String createBNS(Model model){
        BNSProductRequest bnsProductRequest = new BNSProductRequest();
        model.addAttribute("bnsProductRequest", bnsProductRequest);
        return "create_bns";
    }



    public String createBNS_Success(BNSProductRequest bnsProductRequest, MultipartFile[] my_photos) throws IOException {
        //USER
        //PRODUCT
        List<byte[]> imageList = new ArrayList<>();
        for(MultipartFile my_photo : my_photos){
            imageList.add(my_photo.getBytes());
        }

        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(bnsProductRequest.getProduct_name());
        productRequest.setDescription(bnsProductRequest.getProduct_description());
        productRequest.setAmount(bnsProductRequest.getProduct_amount());
        productRequest.setCategory(bnsProductRequest.getProduct_category());
        productRequest.setSellerId(bnsProductRequest.getProduct_sellerId());
        productRequest.setImageList(imageList);
        logger.info(productRequest.toString());
        productClient.createProduct(productRequest);
        logger.info("DATA SENT TO PRODUCT");
        return "redirect:/products/create";
        //IMAGE
    }




//    public BNSResponse createBNS({
//
//        //PRODUCT
//        ProductRequest productRequest = new ProductRequest();
//        productRequest.setName(bnsRequest.getProduct_name());
//        productRequest.setDescription(bnsRequest.getProduct_description());
//        productRequest.setAmount(bnsRequest.getProduct_amount());
//        productRequest.setCategory(bnsRequest.getProduct_category());
//        productRequest.setSellerId(bnsRequest.getProduct_sellerId());
//        productRequest.setImageList(bnsRequest.getProduct_imageList());
//
//        //IMAGE
//
//
//
//        productClient.createProduct();
//    }



}
