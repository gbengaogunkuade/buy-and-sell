package com.ogunkuade.productservice.service;

import com.ogunkuade.productservice.dto.ProductRequest;
import com.ogunkuade.productservice.dto.ProductResponse;
import com.ogunkuade.productservice.entity.Product;
import com.ogunkuade.productservice.exception.ProductNotFoundException;
import com.ogunkuade.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRestService {

    private ProductRepository productRepository;

    public ProductRestService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        Product productNew = new Product();
        productNew.setName(productRequest.getName());
        productNew.setDescription(productRequest.getDescription());
        productNew.setAmount(productRequest.getAmount());
        productNew.setCategory(productRequest.getCategory());
        productNew.setAvailable(true);
        productNew.setSellerId(productRequest.getSellerId());
        productNew.setImageList(productRequest.getImageList());
        Product savedProduct = productRepository.save(productNew);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setDescription(savedProduct.getDescription());
        productResponse.setAmount(savedProduct.getAmount());
        productResponse.setCategory(savedProduct.getCategory());
        productResponse.setAvailable(savedProduct.getAvailable());
        productResponse.setSellerId(savedProduct.getSellerId());
        productResponse.setImageList(savedProduct.getImageList());
        return productResponse;
    }




    public Product getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found in our database"));
        return product;
    }



}
