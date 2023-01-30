package com.api_java.demo.model.repositories;

import com.api_java.demo.model.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    ContactEntity findByName(String name);

    ContactEntity findByPhone(String phone);

    Page<ContactEntity> findByNameContainingAndPhoneContaining(String name, String phone, Pageable pageable);

    List<ContactEntity> findByAddress_idLike(Integer id);
}
