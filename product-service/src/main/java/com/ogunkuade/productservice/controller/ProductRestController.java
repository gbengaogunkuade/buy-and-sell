package com.ogunkuade.productservice.controller;


import com.ogunkuade.productservice.dto.ProductRequest;
import com.ogunkuade.productservice.dto.ProductResponse;
import com.ogunkuade.productservice.dto.ProductUpdateRequest;
import com.ogunkuade.productservice.repository.ProductRepository;
import com.ogunkuade.productservice.service.ProductRestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductRestService productRestService;
    private final ProductRepository productRepository;


    public ProductRestController(ProductRestService productRestService, ProductRepository productRepository) {
        this.productRestService = productRestService;
        this.productRepository = productRepository;
    }



    //CREATE PRODUCT
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse creatingProduct(@RequestBody ProductRequest productRequest){
        return productRestService.createProduct(productRequest);
    }



    //UPDATE PRODUCT
    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updatingProduct(@RequestBody ProductUpdateRequest productUpdateRequest, @PathVariable Long id) throws FileNotFoundException {
        return productRestService.updateProduct(productUpdateRequest, id);
    }



    //GET ALL PRODUCTS
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> gettingAllProducts(){
        return productRestService.getAllProducts();
    }



    //GET PRODUCT BY SELLER ID
    @GetMapping("/{id}/sellerId")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> gettingProductsBySellerId(@PathVariable Long id) throws FileNotFoundException {
        return productRestService.getProductsBySellerId(id);
    }



    //GET PRODUCT BY NAME
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse gettingProductByName(@PathVariable String name) throws Exception{
        return productRestService.getProductByName(name);
    }



    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse gettingProductById(@PathVariable Long id) throws Exception{
        return productRestService.getProductById(id);
    }



    //DELETE PRODUCT BY ID
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deletingProductById(@PathVariable Long id) throws Exception{
        return productRestService.deleteProductById(id);
    }




}
