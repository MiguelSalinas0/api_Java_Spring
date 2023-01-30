package com.api_java.demo.model.repositories;

import com.api_java.demo.model.entities.AddressEntity;
import com.api_java.demo.model.entities.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    Page<AddressEntity> findByStreetContainingAndNumberContaining(String street, String number, Pageable pageable);

}
