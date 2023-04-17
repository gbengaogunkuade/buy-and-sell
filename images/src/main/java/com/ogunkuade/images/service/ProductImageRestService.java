package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.ProductImageResponse;
import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.entity.ProductImage;
import com.ogunkuade.images.entity.UserImage;
import com.ogunkuade.images.repository.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductImageRestService {

    String CURRENT_DIR = System.getProperty("user.dir");
    Path IMAGE_PATH = Path.of(CURRENT_DIR + "/src/main/resources/static/images");

    Path UPLOAD_PATH;
    Path localImage;
    String imageIDStr;

    ProductImage productImage;
    ProductImage savedProductImage;
    List<ProductImage> productImageList;

    ProductImageResponse productImageResponse;
    List<ProductImageResponse> productImageResponseList;

    Path createLocalImage;
    String nameOfCreatedLocalImage;


    private final ProductImageRepository productImageRepository;

    public ProductImageRestService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }


    //POST MULTIPLE IMAGES
    public ProductImageResponse imageRestUpload(List<byte[]> imageList, Long id) throws IOException {
        //SAVING IMAGES TO DATABASE
        productImage = new ProductImage();
        productImage.setProductId(id);
        productImage.setImageList(imageList);
        savedProductImage = productImageRepository.save(productImage);

        productImageResponse = new ProductImageResponse();
        productImageResponse.setId(savedProductImage.getId());
        productImageResponse.setProductId(savedProductImage.getProductId());
        productImageResponse.setImageList(savedProductImage.getImageList());

        return productImageResponse;
    }




    //GET SINGLE IMAGE
    public ProductImageResponse getRestImage(Long id) throws IOException {
        //RETRIEVING IMAGE FROM DATABASE
        if(productImageRepository.existsById(id)){
            productImage = productImageRepository.findById(id).get();
            productImageResponse = new ProductImageResponse();
            productImageResponse.setId(productImage.getId());
            productImageResponse.setProductId(productImage.getProductId());
            productImageResponse.setImageList(productImage.getImageList());
            return productImageResponse;
        } else{
            throw new FileNotFoundException("Image not found");
        }
    }




    //GET ALL IMAGES
    public List<ProductImageResponse> getAllRestImage() {
        productImageResponseList = new ArrayList<>();
        productImageList = productImageRepository.findAll();
        for(ProductImage productImage : productImageList){
            productImageResponse = new ProductImageResponse();
            productImageResponse.setId(productImage.getId());
            productImageResponse.setProductId(productImage.getProductId());
            productImageResponse.setImageList(productImage.getImageList());
            productImageResponseList.add(productImageResponse);
        }
        return productImageResponseList;
    }









}
