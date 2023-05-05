package com.ogunkuade.microservicesmanager.service;


import com.ogunkuade.microservicesmanager.dto.*;
import com.ogunkuade.microservicesmanager.entity.User;
import com.ogunkuade.microservicesmanager.exception.UserNotFoundException;
import com.ogunkuade.microservicesmanager.feignclient.AddressClient;
import com.ogunkuade.microservicesmanager.feignclient.ImageClient;
import com.ogunkuade.microservicesmanager.feignclient.ProductClient;
import com.ogunkuade.microservicesmanager.repository.UserRepository;
import feign.FeignException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private UserRestService userRestService;

    public BNSRestService(ProductClient productClient, AddressClient addressClient, ImageClient imageClient, UserRepository userRepository) {
        this.productClient = productClient;
        this.addressClient = addressClient;
        this.imageClient = imageClient;
        this.userRepository = userRepository;
    }




//PRODUCT SERVICES


    //CREATE PRODUCT
    @PreAuthorize("isAuthenticated()")
    public BNSProductResponseDto createProduct(String name, String description, String amount, String category,  MultipartFile[] imageList) throws Exception {
        BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
        try{
            //SECURITY
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User userFound = null;
            if(userRepository.existsByUsername(username)){
                userFound = userRepository.findByUsername(username);
            }
            //PRODUCT
            ProductRequestDto productRequestDto = new ProductRequestDto();
            productRequestDto.setName(name);
            productRequestDto.setDescription(description);
            productRequestDto.setAmount(amount);
            productRequestDto.setCategory(category);
            productRequestDto.setSellerId(userFound.getId());
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
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return bnsProductResponseDto;
    }






    //UPDATE PRODUCT
    @PreAuthorize("isAuthenticated()")
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
        catch (FeignException e){
            throw new Exception(e.getMessage());
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
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
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
        catch (FeignException e){
            throw new Exception(e.getMessage());
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
        catch (FeignException e){
            throw new Exception(e.getMessage());
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
        catch (FeignException e){
            throw new Exception(e.getMessage());
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
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return "Product successfully deleted";
    }







// USER


    //CREATE USER
    public BNSUserResponseDto createRestUser(String username, String password, String password2, String firstname, String lastname, String gender, String email, MultipartFile image, String lane1, String lane2, String zip, String state) throws Exception {
        BNSUserResponseDto bnsUserResponseDto = new BNSUserResponseDto();
        try{
            //user
            UserRequestDto userRequestDto = new UserRequestDto();
            userRequestDto.setUsername(username);
            userRequestDto.setPassword(password);
            userRequestDto.setPassword2(password2);
            userRequestDto.setFirstname(firstname);
            userRequestDto.setLastname(lastname);
            userRequestDto.setGender(gender);
            userRequestDto.setEmail(email);
            UserResponseDto userResponseDto = userRestService.createUser(userRequestDto);
            //image
            ImageRequest imageRequest = new ImageRequest();
            imageRequest.setName(image.getOriginalFilename());
            imageRequest.setImage(image.getBytes());
            UserImageResponseDto userImageResponseDto = imageClient.imageUserRestUploading(imageRequest, userResponseDto.getId());
            //address
            AddressRequestDto addressRequestDto = new AddressRequestDto();
            addressRequestDto.setLane1(lane1);
            addressRequestDto.setLane2(lane2);
            addressRequestDto.setZip(zip);
            addressRequestDto.setState(state);
            AddressResponseDto addressResponseDto = addressClient.savingAddress(addressRequestDto);
            //RESPONSE
            if(userResponseDto != null){
                bnsUserResponseDto.setUserResponseDto(userResponseDto);
            }
            if(userImageResponseDto != null){
                bnsUserResponseDto.setUserImageResponseDto(userImageResponseDto);
            }
            if(addressResponseDto != null){
                bnsUserResponseDto.setAddressResponseDto(addressResponseDto);
            }
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return bnsUserResponseDto;
    }





    //UPDATE USER
    @PreAuthorize("isAuthenticated()")
    public BNSUserResponseDto updateRestUser(String firstname, String lastname, String gender, String email, MultipartFile image, String lane1, String lane2, String zip, String state, Long id) throws Exception {
        BNSUserResponseDto bnsUserResponseDto = new BNSUserResponseDto();
        try{
            //user
            UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
            userUpdateRequestDto.setFirstname(firstname);
            userUpdateRequestDto.setLastname(lastname);
            userUpdateRequestDto.setGender(gender);
            userUpdateRequestDto.setEmail(email);
            UserResponseDto userResponseDto = userRestService.updateUserById(userUpdateRequestDto, id);
            //image
            ImageRequest imageRequest = new ImageRequest();
            imageRequest.setName(image.getOriginalFilename());
            imageRequest.setImage(image.getBytes());
            UserImageResponseDto userImageResponseDto = imageClient.imageUserRestUploading(imageRequest, id);
            //address
            AddressRequestDto addressRequestDto = new AddressRequestDto();
            addressRequestDto.setLane1(lane1);
            addressRequestDto.setLane2(lane2);
            addressRequestDto.setZip(zip);
            addressRequestDto.setState(state);
            AddressResponseDto addressResponseDto = addressClient.updatingAddressById(addressRequestDto, id);
            //RESPONSE
            if(userResponseDto != null){
                bnsUserResponseDto.setUserResponseDto(userResponseDto);
            }
            if(userImageResponseDto != null){
                bnsUserResponseDto.setUserImageResponseDto(userImageResponseDto);
            }
            if(addressResponseDto != null){
                bnsUserResponseDto.setAddressResponseDto(addressResponseDto);
            }
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return bnsUserResponseDto;
    }






    //GET USER BY ID
    @PreAuthorize("isAuthenticated()")
    public BNSUserResponseDto getRestUserById(Long id) throws Exception {
        BNSUserResponseDto bnsUserResponseDto = new BNSUserResponseDto();
        try{
            //user
            UserResponseDto userResponseDto = userRestService.getUserById(id);
            //image
            UserImageResponseDto userImageResponseDto = imageClient.gettingUserRestImageByUserId(id);
            //address
            AddressResponseDto addressResponseDto = addressClient.gettingAddressById(id);
            //RESPONSE
            if(userResponseDto != null){
                bnsUserResponseDto.setUserResponseDto(userResponseDto);
            }
            if(userImageResponseDto != null){
                bnsUserResponseDto.setUserImageResponseDto(userImageResponseDto);
            }
            if(addressResponseDto != null){
                bnsUserResponseDto.setAddressResponseDto(addressResponseDto);
            }
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return bnsUserResponseDto;
    }




    //GET ALL USERS
    @PreAuthorize("isAuthenticated()")
    public List<BNSUserResponseDto> getAllRestUsers() throws Exception {
        List<BNSUserResponseDto> bnsUserResponseDtoList = new ArrayList<>();
        try{
            //user
            List<UserResponseDto> userResponseDtoList = userRestService.getAllUsers();
            //RESPONSE
            for(UserResponseDto userResponseDto : userResponseDtoList){
                BNSUserResponseDto bnsUserResponseDto = new BNSUserResponseDto();
                //get image by id
                UserImageResponseDto userImageResponseDto = imageClient.gettingUserRestImageByUserId(userResponseDto.getId());
                //get address by id
                AddressResponseDto addressResponseDto = addressClient.gettingAddressById(userResponseDto.getId());
                bnsUserResponseDto.setUserResponseDto(userResponseDto);
                bnsUserResponseDto.setUserImageResponseDto(userImageResponseDto);
                bnsUserResponseDto.setAddressResponseDto(addressResponseDto);
                bnsUserResponseDtoList.add(bnsUserResponseDto);
            }
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return bnsUserResponseDtoList;
    }




    //DELETE USER BY ID
    public String deleteRestUserById(Long id) throws Exception {
        try{
            //delete user
            userRestService.deleteUserById(id);
            //delete image
            imageClient.deletingRestImageById(id);
            //delete address
            addressClient.deletingAddressById(id);
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
        return "USER SUCCESSFULLY DELETED";
    }







}
