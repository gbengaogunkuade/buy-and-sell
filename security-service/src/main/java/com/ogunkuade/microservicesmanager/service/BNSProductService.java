//package com.ogunkuade.microservicesmanager.service;
//
//
//import com.ogunkuade.microservicesmanager.dto.ProductRequestDto;
//import com.ogunkuade.microservicesmanager.dto.ProductImageResponseDto;
//import com.ogunkuade.microservicesmanager.feignclient.ProductImageClient;
//import com.ogunkuade.microservicesmanager.feignclient.ProductClient;
//import com.ogunkuade.microservicesmanager.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class BNSProductService {
//
//    Logger logger = LoggerFactory.getLogger(BNSProductService.class);
//
//    private UserRepository userRepository;
//    private ProductClient productClient;
//    private ProductImageClient productImageClient;
//
//    public BNSProductService(UserRepository userRepository, ProductClient productClient, ProductImageClient productImageClient) {
//        this.userRepository = userRepository;
//        this.productClient = productClient;
//        this.productImageClient = productImageClient;
//    }
//
//
//
//    public String getBNSProductPage(Model model){
//        return "index";
//    }
//
//    public String getBNSProductCart(Model model){
//        return "cart";
//    }
//
//
//
//    public String createBNS(Model model){
//        ProductRequestDto productRequestDto = new ProductRequestDto();
//        model.addAttribute("bnsProductRequest", productRequestDto);
//        return "create_bns";
//    }
//
//
//
//    public String createBNS_Success(ProductRequestDto productRequestDto, MultipartFile[] my_photos) throws IOException {
//
//        //USER
//
//        //PRODUCT
//        ProductRequestDto productRequest = new ProductRequestDto();
//        productRequest.setName(productRequestDto.getProduct_name());
//        productRequest.setDescription(productRequestDto.getProduct_description());
//        productRequest.setAmount(productRequestDto.getProduct_amount());
//        productRequest.setCategory(productRequestDto.getProduct_category());
//        productRequest.setSellerId(productRequestDto.getProduct_sellerId());
//        logger.info(productRequest.toString());
//        ProductResponse savedProductResponse = productClient.createProduct(productRequest);
//        logger.info("DATA SENT TO PRODUCT");
//
//
//        //IMAGES
//        List<byte[]> imageList = new ArrayList<>();
//
//        for(MultipartFile my_photo : my_photos){
//            imageList.add(my_photo.getBytes());
//        }
//
//        logger.info(imageList.toString());
//
//        ProductImageResponseDto productImageResponsesDto = productImageClient.imageRestUploading(imageList, savedProductResponse.getId());
//
//        logger.info("DATA SENT TO PRODUCT IMAGE");
//
//        return "redirect:/products/create";
//        //IMAGE
//    }
//
//
//
//
////    public BNSResponse createBNS({
////
////        //PRODUCT
////        ProductRequest productRequest = new ProductRequest();
////        productRequest.setName(bnsRequest.getProduct_name());
////        productRequest.setDescription(bnsRequest.getProduct_description());
////        productRequest.setAmount(bnsRequest.getProduct_amount());
////        productRequest.setCategory(bnsRequest.getProduct_category());
////        productRequest.setSellerId(bnsRequest.getProduct_sellerId());
////        productRequest.setImageList(bnsRequest.getProduct_imageList());
////
////        //IMAGE
////
////
////
////        productClient.createProduct();
////    }
//
//
//
//}
