//package com.ogunkuade.images.controller;
//
//
//import com.ogunkuade.images.service.ImageService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//public class ImageController {
//
//    private ImageService imageService;
//
//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
//
//    //HOME
//    @GetMapping("")
//    public String gettingHomePage(Model model){
//        return imageService.getHomePage(model);
//    }
//
//
//    //UPLOAD MULTIPLE IMAGES
//    @GetMapping("/{id}/upload")
//    public String imageUpload(@PathVariable Long id, Model model){
//        return imageService.imageUpload(id, model);
//    }
//
//
//    //UPLOAD MULTIPLE IMAGES COMPLETE
//    @PostMapping("/{id}/upload")
//    public String imageUploadComplete(@PathVariable Long id, @RequestParam MultipartFile[] my_photos, Model model) throws IOException {
//        return imageService.imageUploadComplete(id, my_photos, model);
//    }
//
//
//    //GET SINGLE IMAGE
//    @GetMapping("/{id}")
//    public String gettingUserPage(@PathVariable Long id, Model model) throws IOException {
//        return imageService.getUserPage(id, model);
//    }
//
//
//    //GET ALL IMAGES
//    @GetMapping("/all")
//    public String gettingAllImages(Model model) {
//        return imageService.getAllImages(model);
//    }
//
//
//
//
//}
