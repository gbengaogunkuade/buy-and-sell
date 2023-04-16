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
    public List<ProductImageResponse> imageUploadRestComplete(Long id, MultipartFile[] my_photos) throws IOException {
        productImageResponseList = new ArrayList<>();
        List<byte[]> imageList = new ArrayList<>();
        for(MultipartFile my_photo : my_photos){
            imageList.add(my_photo.getBytes());
        }

        for(MultipartFile my_photo : my_photos){
            //SAVING IMAGES TO DATABASE
            productImage = new ProductImage();
            productImage.setName(my_photo.getOriginalFilename());
            productImage.setType(my_photo.getContentType());
            productImage.setProductId(id);
            productImage.setImageList(imageList);
            savedProductImage = productImageRepository.save(productImage);

            productImageResponse = new ProductImageResponse();
            productImageResponse.setId(savedProductImage.getId());
            productImageResponse.setName(savedProductImage.getName());
            productImageResponse.setType(savedProductImage.getType());
            productImageResponse.setProductId(savedProductImage.getProductId());
            productImageResponse.setImageList(savedProductImage.getImageList());

            productImageResponseList.add(productImageResponse);

            //SAVING IMAGE TO LOCAL DISK
            imageIDStr = id.toString();
            //DEFINE AND CREATE UPLOAD_PATH
            UPLOAD_PATH = Path.of(IMAGE_PATH + File.separator + imageIDStr);
            if(Files.notExists(UPLOAD_PATH)){
                Files.createDirectory(UPLOAD_PATH);
            }
            //DEFINE AND CREATE A LOCAL IMAGE
            localImage = Path.of(UPLOAD_PATH + File.separator + my_photo.getOriginalFilename());
            if(Files.exists(localImage)){
                createLocalImage = Files.write(localImage, my_photo.getBytes());
                nameOfCreatedLocalImage = createLocalImage.getFileName().toString();
            } else{
                Path emptyLocalImage = Files.createFile(localImage);
                createLocalImage = Files.write(emptyLocalImage, my_photo.getBytes());
                nameOfCreatedLocalImage = createLocalImage.getFileName().toString();
            }
        }

        return productImageResponseList;
    }




    //GET SINGLE IMAGE
    public ProductImageResponse getRestImage(Long id) throws IOException {
        //RETRIEVING IMAGE FROM DATABASE
        if(productImageRepository.existsById(id)){
            savedProductImage = productImageRepository.findById(id).get();
            productImageResponse = new ProductImageResponse();
            productImageResponse.setId(savedProductImage.getId());
            productImageResponse.setName(savedProductImage.getName());
            productImageResponse.setType(savedProductImage.getType());
            productImageResponse.setProductId(savedProductImage.getProductId());
            productImageResponse.setImageList(savedProductImage.getImageList());
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
            productImageResponse.setName(productImage.getName());
            productImageResponse.setType(productImage.getType());
            productImageResponse.setProductId(productImage.getProductId());
            productImageResponse.setImageList(productImage.getImageList());
            productImageResponseList.add(productImageResponse);
        }
        return productImageResponseList;
    }









}
