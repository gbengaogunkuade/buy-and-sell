package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.ImageRequest;
import com.ogunkuade.images.dto.ImageRequestRecord;
import com.ogunkuade.images.dto.ProductImageResponse;
import com.ogunkuade.images.entity.ProductImage;
import com.ogunkuade.images.repository.ProductImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


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



    //POST SINGLE IMAGE
    public ProductImageResponse imageRestUpload(ImageRequest imageRequest, Long id) throws IOException {
        productImage = new ProductImage();
        productImage.setName(imageRequest.getName());
        productImage.setImage(imageRequest.getImage());
        productImage.setProductId(id);
        savedProductImage = productImageRepository.save(productImage);

        productImageResponse = new ProductImageResponse();
        productImageResponse.setId(savedProductImage.getId());
        productImageResponse.setName(savedProductImage.getName());
        productImageResponse.setImage(savedProductImage.getImage());
        productImageResponse.setProductId(savedProductImage.getProductId());
        return productImageResponse;
    }






//    //POST MULTIPLE IMAGE
//    public List<ProductImageResponse> imageRestUploadMultiple(MultipartFile[] imageList, Long id) throws IOException {
//        productImageResponseList = new ArrayList<>();
//        if(productImageRepository.existsByProductId(id)){
//            productImageList = productImageRepository.findImagesByProductId(id);
//            for(ProductImage productImageX : productImageList){
//                productImageRepository.delete(productImageX);
//            }
//        }
//
//        for(MultipartFile image : imageList){
//            productImage = new ProductImage();
//            productImage.setName(image.getOriginalFilename());
//            productImage.setImage(image.getBytes());
//            productImage.setProductId(id);
//            savedProductImage = productImageRepository.save(productImage);
//
//            productImageResponse = new ProductImageResponse();
//            productImageResponse.setId(savedProductImage.getId());
//            productImageResponse.setName(savedProductImage.getName());
//            productImageResponse.setImage(savedProductImage.getImage());
//            productImageResponse.setProductId(savedProductImage.getProductId());
//            productImageResponseList.add(productImageResponse);
//        }
//        return productImageResponseList;
//    }





    //POST MULTIPLE IMAGE
    public List<ProductImageResponse> imageRestUploadMultiple(List<ImageRequest> imageRequestList, Long id) throws IOException {
        productImageResponseList = new ArrayList<>();

        if(productImageRepository.existsByProductId(id)){
            productImageList = productImageRepository.findImagesByProductId(id);
            for(ProductImage productImageX : productImageList){
                productImageRepository.delete(productImageX);
            }
        }

        for(ImageRequest imageRequest : imageRequestList){
            productImage = new ProductImage();
            productImage.setName(imageRequest.getName());
            productImage.setImage(imageRequest.getImage());
            productImage.setProductId(id);
            savedProductImage = productImageRepository.save(productImage);

            productImageResponse = new ProductImageResponse();
            productImageResponse.setId(savedProductImage.getId());
            productImageResponse.setName(savedProductImage.getName());
            productImageResponse.setImage(savedProductImage.getImage());
            productImageResponse.setProductId(savedProductImage.getProductId());
            productImageResponseList.add(productImageResponse);
        }
        return productImageResponseList;
    }





    //GET SINGLE IMAGE
    public ProductImageResponse getRestImage(Long id) throws IOException {
        productImage = productImageRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Image Not Found"));
        productImageResponse = new ProductImageResponse();
        productImageResponse.setId(savedProductImage.getId());
        productImageResponse.setName(savedProductImage.getName());
        productImageResponse.setImage(savedProductImage.getImage());
        productImageResponse.setProductId(savedProductImage.getProductId());
        return productImageResponse;
    }



    //GET IMAGES BY PRODUCT ID
    public List<ProductImageResponse> getRestImageByProductId(Long id) throws IOException {
        if(productImageRepository.existsByProductId(id)){
            productImageResponseList = new ArrayList<>();
            productImageList = productImageRepository.findImagesByProductId(id);
            for(ProductImage productImage : productImageList){
                productImageResponse = new ProductImageResponse();
                productImageResponse.setId(productImage.getId());
                productImageResponse.setName(productImage.getName());
                productImageResponse.setImage(productImage.getImage());
                productImageResponse.setProductId(productImage.getProductId());
                productImageResponseList.add(productImageResponse);
            }
            return productImageResponseList;
        } else{
            throw new FileNotFoundException("Image With The Product Id Not Found");
        }
    }



    //GET ALL IMAGES
    public List<ProductImageResponse> getAllRestImage() {
        productImageResponseList = new ArrayList<>();
        productImageList = productImageRepository.findAll();
        for(ProductImage productImage : productImageList){
            productImageResponse = new ProductImageResponse();
            productImageResponse.setId(productImage.getId());
            productImageResponse.setName(productImage.getName());
            productImageResponse.setImage(productImage.getImage());
            productImageResponse.setProductId(productImage.getProductId());
            productImageResponseList.add(productImageResponse);
        }
        return productImageResponseList;
    }





}
