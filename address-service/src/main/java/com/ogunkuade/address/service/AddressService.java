package com.ogunkuade.address.service;


import com.ogunkuade.address.dto.AddressRequest;
import com.ogunkuade.address.dto.AddressResponse;
import com.ogunkuade.address.entity.Address;
import com.ogunkuade.address.exception.AddressNotFoundException;
import com.ogunkuade.address.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AddressService {


    private AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }



    public Boolean checkForId(Long id){
        return addressRepository.existsById(id);
    }



    //  SAVE ADDRESS
    public AddressResponse saveAddress(AddressRequest addressRequest){
        Address address = new Address();
        address.setLane1(addressRequest.getLane1());
        address.setLane2(addressRequest.getLane2());
        address.setZip(addressRequest.getZip());
        address.setState(addressRequest.getState());
        Address savedAddress = addressRepository.save(address);
        AddressResponse addressResponse = new AddressResponse(
                savedAddress.getId(),
                savedAddress.getLane1(),
                savedAddress.getLane2(),
                savedAddress.getZip(),
                savedAddress.getState()
        );
        return addressResponse;
    }



    //  UPDATE ADDRESS BY ID
    public AddressResponse updateAddressById(AddressRequest addressRequest, Long id) throws AddressNotFoundException {
        Address addressToBeUpdated = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(String.format("an address with the id %d does not exist", id)));
        addressToBeUpdated.setLane1(addressRequest.getLane1());
        addressToBeUpdated.setLane2(addressRequest.getLane2());
        addressToBeUpdated.setZip(addressRequest.getZip());
        addressToBeUpdated.setState(addressRequest.getState());
        Address updatedAddress = addressRepository.save(addressToBeUpdated);

        AddressResponse addressResponseNew = new AddressResponse();
        addressResponseNew.setId(updatedAddress.getId());
        addressResponseNew.setLane1(updatedAddress.getLane1());
        addressResponseNew.setLane2(updatedAddress.getLane2());
        addressResponseNew.setZip(updatedAddress.getZip());
        addressResponseNew.setState(updatedAddress.getState());
        return addressResponseNew;
    }


    //  GET ADDRESS BY ID
    public AddressResponse getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(String.format("an address with the id %d does not exist", id)));
        AddressResponse addressResponse = new AddressResponse(
                address.getId(),
                address.getLane1(),
                address.getLane2(),
                address.getZip(),
                address.getState()
        );
        return addressResponse;
    }





    //  GET ALL ADDRESSES
    public List<AddressResponse> getAllAddresses(){
        List<AddressResponse> addressResponseList = new ArrayList<>();
        List<Address> addressList = addressRepository.findAll();
        for(Address address : addressList){
            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setId(address.getId());
            addressResponse.setLane1(address.getLane1());
            addressResponse.setLane2(address.getLane2());
            addressResponse.setZip(address.getZip());
            addressResponse.setState(address.getState());
            addressResponseList.add(addressResponse);
        }
        return addressResponseList;
    }


//


    //  GET ALL ADDRESSES WITH PAGINATION
    public List<AddressResponse> getAllAddressesWithPagination(int pageNumber, int pageSize){
        List<AddressResponse> addressResponseList = new ArrayList<>();

        Page<Address> paginatedAddressList = addressRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<Address> addressList = paginatedAddressList.stream().toList();

        for(Address address : addressList){
            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setId(address.getId());
            addressResponse.setLane1(address.getLane1());
            addressResponse.setLane2(address.getLane2());
            addressResponse.setZip(address.getZip());
            addressResponse.setState(address.getState());
            addressResponseList.add(addressResponse);
        }
        return addressResponseList;
    }




    //  DELETE ADDRESS BY ID
    public String deletingAddressById(Long id) {
        Address addressToBeDeleted = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(String.format("an address with the id %d does not exist", id)));
        addressRepository.delete(addressToBeDeleted);
        return "Address with the requested ID has been deleted";
    }




}
