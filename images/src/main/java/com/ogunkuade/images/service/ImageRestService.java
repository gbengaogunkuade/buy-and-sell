package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.ImageResponse;
import com.ogunkuade.images.entity.Image;
import com.ogunkuade.images.repository.ImageRepository;
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
public class ImageRestService {

    String CURRENT_DIR = System.getProperty("user.dir");
    Path IMAGE_PATH = Path.of(CURRENT_DIR + "/src/main/resources/static/images");

    Path UPLOAD_PATH;
    Path localImage;
    String imageIDStr;

    Image image;
    Image savedImage;
    List<Image> imageList;

    Path createLocalImage;
    String nameOfCreatedLocalImage;

    ImageResponse imageResponse;
    List<ImageResponse> imageResponseList;


    private final ImageRepository imageRepository;

    public ImageRestService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }




    //POST MULTIPLE IMAGES
    public List<ImageResponse> imageUploadRestComplete(Long id, MultipartFile[] my_photos) throws IOException {
        imageResponseList = new ArrayList<>();
        for(MultipartFile my_photo : my_photos){
            //SAVING IMAGES TO DATABASE
            image = new Image();
            image.setName(my_photo.getOriginalFilename());
            image.setType(my_photo.getContentType());
            image.setProductId(id);
            image.setImage(my_photo.getBytes());
            savedImage = imageRepository.save(image);

            imageResponse = new ImageResponse();
            imageResponse.setId(savedImage.getId());
            imageResponse.setName(savedImage.getName());
            imageResponse.setType(savedImage.getType());
            imageResponse.setProductId(savedImage.getProductId());
            imageResponse.setImage(savedImage.getImage());

            imageResponseList.add(imageResponse);

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

        return imageResponseList;
    }




    //GET SINGLE IMAGE
    public ImageResponse getRestImage(Long id) throws IOException {
        //RETRIEVING IMAGE FROM DATABASE
        if(imageRepository.existsById(id)){
            Image image = imageRepository.findById(id).get();
            imageResponse = new ImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setName(image.getName());
            imageResponse.setType(image.getType());
            imageResponse.setProductId(image.getProductId());
            imageResponse.setImage(image.getImage());
            return imageResponse;
        } else{
            throw new FileNotFoundException("Image not found");
        }
    }




    //GET ALL IMAGES
    public List<ImageResponse> getAllRestImage() {
        imageResponseList = new ArrayList<>();
        imageList = imageRepository.findAll();
        for(Image image : imageList){
            imageResponse = new ImageResponse();
            imageResponse.setId(image.getId());
            imageResponse.setName(image.getName());
            imageResponse.setType(image.getType());
            imageResponse.setProductId(image.getProductId());
            imageResponse.setImage(image.getImage());
            imageResponseList.add(imageResponse);
        }
        return imageResponseList;
    }





}
