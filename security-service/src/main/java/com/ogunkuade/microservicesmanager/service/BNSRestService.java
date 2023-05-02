package com.ogunkuade.microservicesmanager.service;


import com.ogunkuade.microservicesmanager.dto.*;
import com.ogunkuade.microservicesmanager.feignclient.AddressClient;
import com.ogunkuade.microservicesmanager.feignclient.ImageClient;
import com.ogunkuade.microservicesmanager.feignclient.ProductClient;
import com.ogunkuade.microservicesmanager.repository.UserRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;



@Service
public class BNSRestService {

    //LOGGING
    Logger logger = LoggerFactory.getLogger(BNSRestService.class);

    private final ProductClient productClient;
    private final AddressClient addressClient;
    private final ImageClient imageClient;

    private final UserRepository userRepository;

    public BNSRestService(ProductClient productClient, AddressClient addressClient, ImageClient imageClient, UserRepository userRepository) {
        this.productClient = productClient;
        this.addressClient = addressClient;
        this.imageClient = imageClient;
        this.userRepository = userRepository;
    }



//    @PreAuthorize("isAuthenticated()")
    //CREATE PRODUCT
    public BNSProductResponseDto createProduct(String name, String description, String amount, String category, String sellerId, MultipartFile[] imageList) throws Exception {
        BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
        try{
            //PRODUCT
            ProductRequestDto productRequestDto = new ProductRequestDto();
            productRequestDto.setName(name);
            productRequestDto.setDescription(description);
            productRequestDto.setAmount(amount);
            productRequestDto.setCategory(category);
            productRequestDto.setSellerId(Long.valueOf(sellerId));
            ProductResponseDto productResponseDto = productClient.createProduct(productRequestDto);

            //IMAGE
            List<ImageRequest> imageRequestList = new ArrayList<>();

            for(MultipartFile image : imageList){
                ImageRequest imageRequest = new ImageRequest();
                imageRequest.setName(image.getOriginalFilename());
                imageRequest.setImage(image.getBytes());
                imageRequestList.add(imageRequest);
            }

            List<ProductImageResponseDto> productImageResponseDtoList = imageClient.imageRestUploadingMultiple(imageRequestList, productResponseDto.getId());

            //RESPONSE
            if(productResponseDto != null){
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
            }
            if(productImageResponseDtoList != null){
                bnsProductResponseDto.setProductImageResponseDtoList(productImageResponseDtoList);
            }

        }
        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                throw new Exception("bad request - product creation failed");
            }
        }


        return bnsProductResponseDto;
    }





    //UPDATE PRODUCT
//    @PreAuthorize("isAuthenticated()")
    public BNSProductResponseDto updateProduct(String name, String description, String amount, String category, String id, MultipartFile[] imageList) throws Exception {
        BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
        try{
            //PRODUCT
            ProductUpdateRequestDto productUpdateRequestDto = new ProductUpdateRequestDto();
            productUpdateRequestDto.setName(name);
            productUpdateRequestDto.setDescription(description);
            productUpdateRequestDto.setAmount(amount);
            productUpdateRequestDto.setCategory(category);
            ProductResponseDto productResponseDto = productClient.updatingProduct(productUpdateRequestDto, Long.valueOf(id));

            //IMAGE
            List<ImageRequest> imageRequestList = new ArrayList<>();

            for(MultipartFile image : imageList){
                ImageRequest imageRequest = new ImageRequest();
                imageRequest.setName(image.getOriginalFilename());
                imageRequest.setImage(image.getBytes());
                imageRequestList.add(imageRequest);
            }

            List<ProductImageResponseDto> productImageResponseDtoList = imageClient.imageRestUploadingMultiple(imageRequestList, productResponseDto.getId());

            //RESPONSE
            if(productResponseDto != null){
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
            }
            if(productImageResponseDtoList != null){
                bnsProductResponseDto.setProductImageResponseDtoList(productImageResponseDtoList);
            }
        }

        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                throw new Exception("bad request - product update failed");
            }
        }

        return bnsProductResponseDto;
    }





    //GET PRODUCT BY ID
//    @PreAuthorize("isAuthenticated()")
    public BNSProductResponseDto getProductById(Long id) throws Exception {
        BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
        try{
            //get product
            ProductResponseDto productResponseDto = productClient.gettingProductById(id);
            //get productImage
            List<ProductImageResponseDto> productImageResponseDtoList = imageClient.gettingRestImageByProductId(id);
            if(productResponseDto != null){
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
            }
            if(productImageResponseDtoList != null){
                bnsProductResponseDto.setProductImageResponseDtoList(productImageResponseDtoList);
            }
            return bnsProductResponseDto;
        }
        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                throw new Exception("bad request - product with this id not found");
            }
        }

        return bnsProductResponseDto;
    }




    //GET ALL PRODUCTS
//    @PreAuthorize("isAuthenticated()")
    public List<BNSProductResponseDto> getAllProducts() throws Exception {
        List<BNSProductResponseDto> bnsProductResponseDtoList = new ArrayList<>();
        try{
            //get all products
            List<ProductResponseDto> productResponseDtoList = productClient.gettingAllProducts();
            //iterate through products
            for(ProductResponseDto productResponseDto : productResponseDtoList){
                BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
                //get all productImages for each product
                List<ProductImageResponseDto> productImageResponseDtoList = imageClient.gettingRestImageByProductId(productResponseDto.getId());
                bnsProductResponseDto.setProductImageResponseDtoList(productImageResponseDtoList);
                bnsProductResponseDtoList.add(bnsProductResponseDto);
            }
        }

        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                throw new Exception("bad request");
            }
        }

        return bnsProductResponseDtoList;
    }




    //GET PRODUCT BY NAME
//    @PreAuthorize("isAuthenticated()")
    public BNSProductResponseDto getProductByName(String name) throws Exception {
        BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
        try{
            //get product
            ProductResponseDto productResponseDto = productClient.gettingProductByName(name);
            //get productImage
            List<ProductImageResponseDto> productImageResponseDtoList = imageClient.gettingRestImageByProductId(productResponseDto.getId());
            if(productResponseDto != null){
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
            }
            if(productImageResponseDtoList != null){
                bnsProductResponseDto.setProductImageResponseDtoList(productImageResponseDtoList);
            }
        }
        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                logger.warn("PRODUCT FOR THIS SELLER NOT FOUND");
                throw new Exception("bad request - product with this name not found");
            }
        }

        return bnsProductResponseDto;
    }





    //GET PRODUCTS BY SELLERID
//    @PreAuthorize("isAuthenticated()")
    public List<BNSProductResponseDto> getProductBySellerId(Long id) throws Exception {
        List<BNSProductResponseDto> bnsProductResponseDtoList = new ArrayList<>();
        try{
            //get products
            List<ProductResponseDto> productResponseDtoList = productClient.gettingProductsBySellerId(id);
            for(ProductResponseDto productResponseDto : productResponseDtoList){
                BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
                //get productImages for each product
                List<ProductImageResponseDto> productImageResponseDtoList = imageClient.gettingRestImageByProductId(productResponseDto.getId());
                bnsProductResponseDto.setProductImageResponseDtoList(productImageResponseDtoList);
                bnsProductResponseDtoList.add(bnsProductResponseDto);
            }
        }
        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                logger.warn("PRODUCT FOR THIS SELLER NOT FOUND");
                throw new Exception("bad request - products for this seller not found");
            }
        }

        return bnsProductResponseDtoList;
    }






    //DELETE PRODUCT BY ID
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_ADMIN')")
    public String deleteProductById(Long id) throws Exception {
        try{
            //get product
            productClient.deletingProductById(id);
            //get productImages
            imageClient.deletingRestImageById(id);
        }
        catch (Exception e){
            if (e instanceof NullPointerException) {
                logger.error("NULL POINTER EXCEPTION");
                throw new Exception("null pointer exception");
            }
            if (e instanceof FeignException.ServiceUnavailable) {
                logger.error("CLIENT IS UNAVAILABLE");
                throw new Exception("client unavailable");
            }
            if (e instanceof FeignException.BadRequest) {
                logger.warn("PRODUCT FOR THIS SELLER NOT FOUND");
                throw new Exception("bad request - product with this id not found");
            }
        }

        return "Product successfully deleted";
    }







}
