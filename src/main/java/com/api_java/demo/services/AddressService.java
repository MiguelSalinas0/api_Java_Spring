package com.api_java.demo.services;

import com.api_java.demo.model.entities.AddressEntity;
import com.api_java.demo.model.repositories.AddressRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Page<AddressEntity> findAll(Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return addressRepository.findAll(pageable);
    }

    public Page<AddressEntity> findAllWithFilter(String street, String number, Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return addressRepository.findByStreetContainingAndNumberContaining(street, number, pageable);
    }

}
