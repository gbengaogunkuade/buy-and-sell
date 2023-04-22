package com.ogunkuade.productservice.service;

import com.ogunkuade.productservice.dto.ProductRequest;
import com.ogunkuade.productservice.dto.ProductResponse;
import com.ogunkuade.productservice.entity.Product;
import com.ogunkuade.productservice.exception.ProductNotFoundException;
import com.ogunkuade.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductRestService {

    private ProductRepository productRepository;

    public ProductRestService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    //CREATE NEW PRODUCTS
    public ProductResponse createProduct(ProductRequest productRequest){
        Product productNew = new Product();
        productNew.setName(productRequest.getName());
        productNew.setDescription(productRequest.getDescription());
        productNew.setAmount(productRequest.getAmount());
        productNew.setCategory(productRequest.getCategory());
        productNew.setAvailable(true);
        productNew.setSellerId(productRequest.getSellerId());
        Product savedProduct = productRepository.save(productNew);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setDescription(savedProduct.getDescription());
        productResponse.setAmount(savedProduct.getAmount());
        productResponse.setCategory(savedProduct.getCategory());
        productResponse.setAvailable(savedProduct.getAvailable());
        productResponse.setSellerId(savedProduct.getSellerId());
        return productResponse;
    }




    //GET ALL PRODUCTS
    public List<ProductResponse> getAllProducts(){
        List<ProductResponse> productResponseList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product product :  productList){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setAmount(product.getAmount());
            productResponse.setCategory(product.getCategory());
            productResponse.setAvailable(product.getAvailable());
            productResponse.setSellerId(product.getSellerId());
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }




    public List<ProductResponse> getProductsBySellerId(Long id) throws FileNotFoundException {
        List<ProductResponse> productResponseList = new ArrayList<>();
        if(productRepository.existsProductBySellerId(id)){
            List<Product> productList = productRepository.findProductsBySellerId(id);
            for(Product product : productList){
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(product.getId());
                productResponse.setName(product.getName());
                productResponse.setDescription(product.getDescription());
                productResponse.setAmount(product.getAmount());
                productResponse.setCategory(product.getCategory());
                productResponse.setAvailable(product.getAvailable());
                productResponse.setSellerId(product.getSellerId());
                productResponseList.add(productResponse);
            }
            return productResponseList;
        } else{
            throw new FileNotFoundException("NO PRODUCT FOUND FOR THIS SELLER ID");
        }


    }



    //GET PRODUCT BY ID
    public Product getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found in our database"));
        return product;
    }










}
