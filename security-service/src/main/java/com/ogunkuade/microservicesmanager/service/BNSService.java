package com.ogunkuade.microservicesmanager.service;


import com.ogunkuade.microservicesmanager.dto.*;
import com.ogunkuade.microservicesmanager.feignclient.AddressClient;
import com.ogunkuade.microservicesmanager.feignclient.ImageClient;
import com.ogunkuade.microservicesmanager.feignclient.ProductClient;
import com.ogunkuade.microservicesmanager.repository.RoleRepository;
import com.ogunkuade.microservicesmanager.repository.UserRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.*;




@Service
public class BNSService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductClient productClient;
    private final AddressClient addressClient;
    private final ImageClient imageClient;


    public BNSService(UserRepository userRepository, RoleRepository roleRepository, ProductClient productClient, AddressClient addressClient, ImageClient imageClient) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productClient = productClient;
        this.addressClient = addressClient;
        this.imageClient = imageClient;
    }


    public String getHome(){
        return "index";
    }




    //GET ALL PRODUCTS
    public String getIndexPage(Model model) throws Exception {
        List<GUI_RecentProductResponse> gui_recentProductResponseList = new ArrayList<>();
        List<ProductImageResponseDto> productImageResponseDtoList;
        List<GUI_RecentProductResponse> gui_Four_ResponseList = new ArrayList<>();

        try{
            //get all products
            List<ProductResponseDto> productResponseDtoList = productClient.gettingAllProducts();
            //iterate through products
            for(ProductResponseDto productResponseDto : productResponseDtoList){
                BNSProductResponseDto bnsProductResponseDto = new BNSProductResponseDto();
                bnsProductResponseDto.setProductResponseDto(productResponseDto);
                //get all productImages for each product
                productImageResponseDtoList = imageClient.gettingRestImageByProductId(productResponseDto.getId());
                //add both product and image to GUI
                GUI_RecentProductResponse guiRecentProductResponse = new GUI_RecentProductResponse();
                guiRecentProductResponse.setId(productResponseDto.getId());
                guiRecentProductResponse.setProductName(productResponseDto.getName());
                guiRecentProductResponse.setProductDescription(productResponseDto.getDescription());
                guiRecentProductResponse.setProductAmount(productResponseDto.getAmount());
                guiRecentProductResponse.setProductCategory(productResponseDto.getCategory());
                guiRecentProductResponse.setProductAvailable(productResponseDto.getAvailable());
                guiRecentProductResponse.setProductSellerId(productResponseDto.getSellerId());

                GUI_ProductImageResponse gui_productImageResponse = new GUI_ProductImageResponse();
                gui_productImageResponse.setName(productImageResponseDtoList.get(0).getName());

                byte[] pix = productImageResponseDtoList.get(0).getImage();
                //convert the image from database to base64
                String encodedImage = Base64.getEncoder().encodeToString(pix);
                String base64IMAGE = "data:image/jpeg; base64," + encodedImage;
                gui_productImageResponse.setConvertedImage(base64IMAGE);

                guiRecentProductResponse.setGui_productImageResponse(gui_productImageResponse);

                gui_recentProductResponseList.add(guiRecentProductResponse);
            }
            //get the last four recently posted products
            int size = gui_recentProductResponseList.size();
            int num = 1;
            int i = 1;
            while(num <= 4){
                gui_Four_ResponseList.add(gui_recentProductResponseList.get(size - i));
                num++;
                i++;
            }

            model.addAttribute("gui_Four_ResponseList", gui_Four_ResponseList);
            return "index";
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
    }





    //GET SINGLE PRODUCTS
    public String getSingleProductPage(Long id, Model model) throws Exception {
        List<ProductImageResponseDto> productImageResponseDtoList;
        ProductResponseDto productResponseDto;
//        GUI_ProductResponse guiProductResponse;
        List<GUI_ProductImageResponse> gui_productImageResponseList = new ArrayList<>();

        try{
            //get single product by id
            productResponseDto = productClient.gettingProductById(id);
            //get the productImage for this product
            productImageResponseDtoList = imageClient.gettingRestImageByProductId(id);
            //add both product and image to GUI
            GUI_ProductResponse gui_ProductResponse = new GUI_ProductResponse();
            gui_ProductResponse.setId(productResponseDto.getId());
            gui_ProductResponse.setProductName(productResponseDto.getName());
            gui_ProductResponse.setProductDescription(productResponseDto.getDescription());
            gui_ProductResponse.setProductAmount(productResponseDto.getAmount());
            gui_ProductResponse.setProductCategory(productResponseDto.getCategory());
            gui_ProductResponse.setProductAvailable(productResponseDto.getAvailable());
            gui_ProductResponse.setProductSellerId(productResponseDto.getSellerId());

            for(ProductImageResponseDto productImageResponseDto : productImageResponseDtoList){
                byte[] pix = productImageResponseDto.getImage();
                String encodedImage = Base64.getEncoder().encodeToString(pix);
                String base64IMAGE = "data:image/jpeg; base64," + encodedImage;
                GUI_ProductImageResponse gui_productImageResponse = new GUI_ProductImageResponse();
                gui_productImageResponse.setId(productImageResponseDto.getId());
                gui_productImageResponse.setName(productImageResponseDto.getName());
                gui_productImageResponse.setConvertedImage(base64IMAGE);
                gui_productImageResponseList.add(gui_productImageResponse);
            }
            gui_ProductResponse.setGuiProductImageResponseList(gui_productImageResponseList);

            model.addAttribute("gui_ProductResponse", gui_ProductResponse);
            model.addAttribute("productCategory", gui_ProductResponse.getProductCategory());
            return "product-detail";
        }
        catch (FeignException e){
            throw new Exception(e.getMessage());
        }
    }












}
