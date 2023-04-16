//package com.ogunkuade.images.service;
//
//
//import com.ogunkuade.images.dto.ImageResponseRecord;
//import com.ogunkuade.images.entity.Image;
//import com.ogunkuade.images.repository.ImageRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//
//
//@Service
//public class ImageService {
//
//    String CURRENT_DIR = System.getProperty("user.dir");
//    Path IMAGE_PATH = Path.of(CURRENT_DIR + "/src/main/resources/static/images");
//
//    Path UPLOAD_PATH;
//    Path localImage;
//    String imageIDStr;
//
//    Image image;
//    Path createLocalImage;
//    String nameOfCreatedLocalImage;
//
//    ImageResponseRecord imageResponseRecord;
//    List<ImageResponseRecord> imageResponseRecordList;
//
//
//
//    private final ImageRepository imageRepository;
//
//
//    public ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
//
//
//    //HOME PAGE
//    public String getHomePage(Model model){
//        return "index";
//    }
//
//
//
//
//    //UPLOAD MULTIPLE IMAGES
//    public String imageUpload(Long id, Model model){
//        model.addAttribute("userID", id);
//        return "image_upload_form";
//    }
//
//
//
//
//
//    //UPLOAD MULTIPLE IMAGES COMPLETE
//    public String imageUploadComplete(Long id, MultipartFile[] my_photos, Model model) throws IOException {
//
//        for(MultipartFile my_photo : my_photos){
//            //SAVING IMAGES TO DATABASE
//            image = new Image();
//            image.setName(my_photo.getOriginalFilename());
//            image.setType(my_photo.getContentType());
//            image.setProductId(id);
//            image.setImage(my_photo.getBytes());
//            imageRepository.save(image);
//
//            //SAVING IMAGE TO LOCAL DISK
//            imageIDStr = id.toString();
//            //DEFINE AND CREATE UPLOAD_PATH
//            UPLOAD_PATH = Path.of(IMAGE_PATH + File.separator + imageIDStr);
//            if(Files.notExists(UPLOAD_PATH)){
//                Files.createDirectory(UPLOAD_PATH);
//            }
//            //DEFINE AND CREATE A LOCAL IMAGE
//            localImage = Path.of(UPLOAD_PATH + File.separator + my_photo.getOriginalFilename());
//            if(Files.exists(localImage)){
//                createLocalImage = Files.write(localImage, my_photo.getBytes());
//                nameOfCreatedLocalImage = createLocalImage.getFileName().toString();
//            } else{
//                Path emptyLocalImage = Files.createFile(localImage);
//                createLocalImage = Files.write(emptyLocalImage, my_photo.getBytes());
//                nameOfCreatedLocalImage = createLocalImage.getFileName().toString();
//            }
//        }
//
//        return "redirect:/{id}";
//    }
//
//
//
//
//
//    //GET SINGLE IMAGE
//    public String getUserPage(Long id, Model model) throws IOException {
//        //RETRIEVING IMAGE FROM DATABASE
//        if(imageRepository.existsById(id)){
//            Image image = imageRepository.findById(id).get();
//            Long imageID = image.getId();
//            String nameOfImage = image.getName();
//            byte[] imageContent = image.getImage();
//            //CONVERT THE IMAGE FROM DATABASE TO BASE64
//            String encodedImage = Base64.getEncoder().encodeToString(imageContent);
//            String base64IMAGE = "data:image/jpeg; base64," + encodedImage;
//            model.addAttribute("base64IMAGE", base64IMAGE);
//            model.addAttribute("image", image);
//
////            //RETRIEVING IMAGE FROM DATABASE AND SAVING IN LOCAL DISK (Not Necessary)
////            imageIDStr = imageID.toString();
////            UPLOAD_PATH = Path.of(IMAGE_PATH + File.separator + imageIDStr);
////            Path emptyImagePath = Path.of(UPLOAD_PATH  + File.separator + nameOfImage);
////            Files.write(emptyImagePath, imageContent);
//
//            model.addAttribute("nameOfImage", nameOfImage);
//            return "image_page";
//        } else{
//            model.addAttribute("imageDoesNotExist", "imageDoesNotExist");
//            return "image_page";
//        }
//    }
//
//
//
//
//
//    //GET ALL IMAGES
//    public String getAllImages(Model model){
//        imageResponseRecordList = new ArrayList<>();
//        //RETRIEVING ALL IMAGE FROM DATABASE
//        List<Image> imageList = imageRepository.findAll();
//        //CONVERT ALL IMAGES FROM DATABASE TO BASE64
//        for(Image image : imageList){
//            byte[] imageContent = image.getImage();
//            String encodedImage = Base64.getEncoder().encodeToString(imageContent);
//            String base64IMAGE = "data:image/jpeg; base64," + encodedImage;
//            imageResponseRecord = new ImageResponseRecord(base64IMAGE, image);
//            imageResponseRecordList.add(imageResponseRecord);
//        }
//
//        model.addAttribute("imageResponseRecordList", imageResponseRecordList);
//        return "all_images";
//    }
//
//
//
//
//
//
//
//
//
//
//
//}
