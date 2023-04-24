package com.ogunkuade.microservicesmanager.feignclient;


import com.ogunkuade.microservicesmanager.dto.AddressRequestDto;
import com.ogunkuade.microservicesmanager.dto.AddressResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {

    @PostMapping("/create")
    AddressResponseDto savingAddress(@Valid @RequestBody AddressRequestDto addressRequestDto);


    @PutMapping("/{id}/update")
    AddressResponseDto updatingAddressById(@Valid @RequestBody AddressRequestDto addressRequestDto, @PathVariable Long id);


    @GetMapping("/{id}")
    AddressResponseDto gettingAddressById(@PathVariable Long id);


    @GetMapping("/all")
    List<AddressResponseDto> gettingAllAddresses();


    @DeleteMapping("/{id}/delete")
    String deletingAddressById(@PathVariable Long id);



}
