package com.ogunkuade.microservicesmanager.feignclient;


import com.ogunkuade.microservicesmanager.dto.AddressRequestDto;
import com.ogunkuade.microservicesmanager.dto.AddressResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {


    //SAVING ADDRESS
    @PostMapping("/create")
    AddressResponseDto savingAddress(@Valid @RequestBody AddressRequestDto addressRequestDto);


    //UPDATING ADDRESS BY ID
    @PutMapping("/{id}/update")
    AddressResponseDto updatingAddressById(@Valid @RequestBody AddressRequestDto addressRequestDto, @PathVariable Long id);


    //GETTING ADDRESS BY ID
    @GetMapping("/{id}")
    AddressResponseDto gettingAddressById(@PathVariable Long id);


    //GETTING ALL ADDRESSES
    @GetMapping("/all")
    List<AddressResponseDto> gettingAllAddresses();


    //DELETING ADDRESS BY ID
    @DeleteMapping("/{id}/delete")
    String deletingAddressById(@PathVariable Long id);



}


