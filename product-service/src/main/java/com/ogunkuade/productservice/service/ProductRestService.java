package com.ogunkuade.productservice.service;

import com.ogunkuade.productservice.dto.ProductRequest;
import com.ogunkuade.productservice.dto.ProductResponse;
import com.ogunkuade.productservice.dto.ProductUpdateRequest;
import com.ogunkuade.productservice.entity.Product;
import com.ogunkuade.productservice.exception.ProductNotFoundException;
import com.ogunkuade.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductRestService {


    Product productNew;
    Product product;
    List<Product> productList;
    Product savedProduct;
    ProductResponse productResponse;
    List<ProductResponse> productResponseList;
    Product existingProduct;


    private ProductRepository productRepository;

    public ProductRestService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private List<ProductResponse> getListOfProductResponseFromListOfProduct(){
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



    //CREATE NEW PRODUCTS
    public ProductResponse createProduct(ProductRequest productRequest){
        productNew = new Product();
        productNew.setName(productRequest.getName());
        productNew.setDescription(productRequest.getDescription());
        productNew.setAmount(productRequest.getAmount());
        productNew.setCategory(productRequest.getCategory());
        productNew.setAvailable(true);
        productNew.setSellerId(productRequest.getSellerId());
        Product savedProduct = productRepository.save(productNew);

        productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setDescription(savedProduct.getDescription());
        productResponse.setAmount(savedProduct.getAmount());
        productResponse.setCategory(savedProduct.getCategory());
        productResponse.setAvailable(savedProduct.getAvailable());
        productResponse.setSellerId(savedProduct.getSellerId());
        return productResponse;
    }



    //UPDATE PRODUCT BY ID
    public ProductResponse updateProduct(ProductUpdateRequest productUpdateRequest, Long id) throws FileNotFoundException {
        existingProduct = productRepository.findById(id).orElseThrow(() -> new FileNotFoundException("PRODUCT WITH THIS ID NOT FOUND"));
        existingProduct.setName(productUpdateRequest.getName());
        existingProduct.setDescription(productUpdateRequest.getDescription());
        existingProduct.setAmount(productUpdateRequest.getAmount());
        existingProduct.setCategory(productUpdateRequest.getCategory());
        existingProduct.setAvailable(true);
        savedProduct = productRepository.save(existingProduct);

        productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setDescription(savedProduct.getDescription());
        productResponse.setAmount(savedProduct.getAmount());
        productResponse.setCategory(savedProduct.getCategory());
        productResponse.setAvailable(savedProduct.getAvailable());
        productResponse.setSellerId(savedProduct.getSellerId());
        return productResponse;
    }



//    //GET ALL PRODUCTS
//    public List<ProductResponse> getAllProducts(){
//        productResponseList = new ArrayList<>();
//        productList = productRepository.findAll();
//        for(Product product :  productList){
//            ProductResponse productResponse = new ProductResponse();
//            productResponse.setId(product.getId());
//            productResponse.setName(product.getName());
//            productResponse.setDescription(product.getDescription());
//            productResponse.setAmount(product.getAmount());
//            productResponse.setCategory(product.getCategory());
//            productResponse.setAvailable(product.getAvailable());
//            productResponse.setSellerId(product.getSellerId());
//            productResponseList.add(productResponse);
//        }
//        return productResponseList;
//    }



    //GET ALL PRODUCTS
    public List<ProductResponse> getAllProducts(){
        productResponseList = new ArrayList<>();
        productList = productRepository.findAll();
        return getListOfProductResponseFromListOfProduct();
    }



//    //GET PRODUCTS BY SELLER ID
//    public List<ProductResponse> getProductsBySellerId(Long id) throws FileNotFoundException {
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        if(productRepository.existsProductBySellerId(id)){
//            productList = productRepository.findProductsBySellerId(id);
//            for(Product product : productList){
//                productResponse = new ProductResponse();
//                productResponse.setId(product.getId());
//                productResponse.setName(product.getName());
//                productResponse.setDescription(product.getDescription());
//                productResponse.setAmount(product.getAmount());
//                productResponse.setCategory(product.getCategory());
//                productResponse.setAvailable(product.getAvailable());
//                productResponse.setSellerId(product.getSellerId());
//                productResponseList.add(productResponse);
//            }
//            return productResponseList;
//        } else{
//            throw new FileNotFoundException("NO PRODUCT FOUND FOR THIS SELLER ID");
//        }
//    }



    //GET PRODUCTS BY SELLER ID
    public List<ProductResponse> getProductsBySellerId(Long id) throws FileNotFoundException {
        productResponseList = new ArrayList<>();
        if(productRepository.existsProductBySellerId(id)){
            productList = productRepository.findProductsBySellerId(id);
            return getListOfProductResponseFromListOfProduct();
        } else{
            throw new FileNotFoundException("NO PRODUCT FOUND FOR THIS SELLER ID");
        }
    }




    //GET PRODUCT BY NAME
    public ProductResponse getProductByName(String name) throws Exception{
        if(productRepository.existsProductByName(name)){
            product = productRepository.findProductByName(name);
            productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setAmount(product.getAmount());
            productResponse.setCategory(product.getCategory());
            productResponse.setAvailable(product.getAvailable());
            productResponse.setSellerId(product.getSellerId());
            return productResponse;
        } else{
            throw new FileNotFoundException("PRODUCT WITH THIS NAME NOT FOUND");
        }
    }


    //GET PRODUCT BY ID
    public ProductResponse getProductById(Long id) throws Exception{
        product = productRepository.findById(id).orElseThrow(() -> new FileNotFoundException("PRODUCT WITH THIS ID NOT FOUND"));
        productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setAmount(product.getAmount());
        productResponse.setCategory(product.getCategory());
        productResponse.setAvailable(product.getAvailable());
        productResponse.setSellerId(product.getSellerId());
        return productResponse;
    }




    //DELETE PRODUCT BY ID
    public String deleteProductById(Long id) throws Exception{
        product = productRepository.findById(id).orElseThrow(() -> new FileNotFoundException("PRODUCT WITH THIS ID NOT FOUND"));
        productRepository.delete(product);
        return String.format("PRODUCT WITH THE ID %d SUCCESSFULLY DELETED", id);
    }





}
