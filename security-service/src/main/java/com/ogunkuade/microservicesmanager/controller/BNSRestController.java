package com.ogunkuade.microservicesmanager.controller;


import com.ogunkuade.microservicesmanager.dto.BNSProductResponseDto;
import com.ogunkuade.microservicesmanager.dto.BNSUserResponseDto;
import com.ogunkuade.microservicesmanager.service.BNSRestService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("/api")
@Validated
public class BNSRestController {

    private final BNSRestService bnsRestService;

    public BNSRestController(BNSRestService bnsRestService) {
        this.bnsRestService = bnsRestService;
    }






//PRODUCT

    //CREATE PRODUCT
    @PostMapping("/products/create")
    public BNSProductResponseDto creatingProduct(
            @RequestParam @NotBlank(message = "NAME FIELD CAN NOT BE BLANK") String name,
            @RequestParam String description,
            @RequestParam String amount,
            @RequestParam String category,
            @RequestParam MultipartFile[] imageList
    ) throws Exception {
        return bnsRestService.createProduct(name, description, amount, category, imageList);
    }




    //UPDATE PRODUCT
    @PutMapping("/products/{id}/update")
    public BNSProductResponseDto updatingProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String amount,
            @RequestParam String category,
            @RequestParam String id,
            @RequestParam MultipartFile[] imageList
    ) throws Exception {
        return bnsRestService.updateProduct(name, description, amount, category, id, imageList);
    }



    //GET PRODUCT BY ID
    @GetMapping("/products/{id}")
    public BNSProductResponseDto gettingProductById(@PathVariable Long id) throws Exception {
        return bnsRestService.getProductById(id);
    }



    //GET ALL PRODUCTS
    @GetMapping("/products/all")
    public List<BNSProductResponseDto> gettingAllProducts() throws Exception {
        return bnsRestService.getAllProducts();
    }




    //GET PRODUCT BY NAME
    @GetMapping("/products/name/{name}")
    public BNSProductResponseDto gettingProductByName(@PathVariable String name) throws Exception {
        return bnsRestService.getProductByName(name);
    }




    //GET PRODUCTS BY SELLERID
    @GetMapping("/products/seller/{id}")
    public List<BNSProductResponseDto> gettingProductsBySellerId(@PathVariable Long id) throws Exception {
        return bnsRestService.getProductBySellerId(id);
    }




    //DELETE PRODUCT BY ID
    @DeleteMapping("/products/{id}/delete")
    public String deletingProductById(@PathVariable Long id) throws Exception {
        return bnsRestService.deleteProductById(id);
    }





//USER

    //CREATE USER
    @PostMapping("/users/create")
    public BNSUserResponseDto creatingRestUser(
            @RequestParam @NotBlank(message = "USERNAME FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "USERNAME FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String username,
            @RequestParam @NotBlank(message = "PASSWORD FIELD CAN NOT BE BLANK") String password,
            @RequestParam @NotBlank(message = "PASSWORD2 FIELD CAN NOT BE BLANK") String password2,
            @RequestParam @NotBlank(message = "FIRSTNAME FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "FIRSTNAME FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String firstname,
            @RequestParam @NotBlank(message = "LASTNAME FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "LASTNAME FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String lastname,
            @RequestParam @NotBlank(message = "GENDER FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "GENDER FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String gender,
            @RequestParam @NotBlank(message = "EMAIL FIELD CAN NOT BE BLANK") @Email(message = "EMAIL FIELD MUST CONTAINS VALID EMAIL ADDRESS") String email,

            @RequestParam MultipartFile image,

            @RequestParam @NotBlank(message = "LANE1 FIELD CAN NOT BE BLANK")  String lane1,
            @RequestParam @NotBlank(message = "LANE2 FIELD CAN NOT BE BLANK") String lane2,
            @RequestParam @NotBlank(message = "ZIP FIELD CAN NOT BE BLANK") String zip,
            @RequestParam @NotBlank(message = "STATE FIELD CAN NOT BE BLANK") String state
    ) throws Exception {
        return bnsRestService.createRestUser(username, password, password2, firstname, lastname, gender, email, image, lane1, lane2, zip, state);
    }





    //UPDATE USER
    @PutMapping("/users/{id}/update")
    public BNSUserResponseDto updatingRestUser(
            @RequestParam @NotBlank(message = "FIRSTNAME FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "FIRSTNAME FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String firstname,
            @RequestParam @NotBlank(message = "LASTNAME FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "LASTNAME FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String lastname,
            @RequestParam @NotBlank(message = "GENDER FIELD CAN NOT BE BLANK") @Pattern(regexp = "^[a-zA-Z]{3,}$", message = "GENDER FIELD MUST CONTAINS AT LEAST 3 CHARACTERS") String gender,
            @RequestParam @NotBlank(message = "EMAIL FIELD CAN NOT BE BLANK") @Email(message = "EMAIL FIELD MUST CONTAINS VALID EMAIL ADDRESS") String email,

            @RequestParam MultipartFile image,

            @RequestParam @NotBlank(message = "LANE1 FIELD CAN NOT BE BLANK")  String lane1,
            @RequestParam @NotBlank(message = "LANE2 FIELD CAN NOT BE BLANK") String lane2,
            @RequestParam @NotBlank(message = "ZIP FIELD CAN NOT BE BLANK") String zip,
            @RequestParam @NotBlank(message = "STATE FIELD CAN NOT BE BLANK") String state,
            @PathVariable Long id
    ) throws Exception {
        return bnsRestService.updateRestUser(firstname, lastname, gender, email, image, lane1, lane2, zip, state, id);
    }





    //GET USER BY ID
    @GetMapping("/users/{id}")
    public BNSUserResponseDto gettingRestUserById(@PathVariable Long id) throws Exception {
        return bnsRestService.getRestUserById(id);
    }



    //GET ALL USERS
    @GetMapping("/users/all")
    public List<BNSUserResponseDto> gettingAllRestUsers() throws Exception {
        return bnsRestService.getAllRestUsers();
    }



    //DELETE USER BY ID
    @DeleteMapping("/users/{id}/delete")
    public String deletingRestUserById(@PathVariable Long id) throws Exception {
        return bnsRestService.deleteRestUserById(id);
    }



}
