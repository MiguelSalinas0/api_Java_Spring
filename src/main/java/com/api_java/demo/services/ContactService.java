package com.api_java.demo.services;

import com.api_java.demo.exceptions.exceptionskinds.ContactoInexistenteException;
import com.api_java.demo.exceptions.exceptionskinds.ContactoExistenteException;
import com.api_java.demo.model.domain.ContactDTO;
import com.api_java.demo.model.entities.ContactEntity;
import com.api_java.demo.model.mappers.ContactMapper;
import com.api_java.demo.model.repositories.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("contactService")
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public ContactEntity getOne(Integer id){
        return contactRepository.findById(id).orElseThrow(() -> new ContactoInexistenteException("No se encontró el contacto"));
    }

    public Page<ContactEntity> findAll(Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return contactRepository.findAll(pageable);
    }

    public Page<ContactEntity> findAllWithFilter(String name, String phone, Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return contactRepository.findByNameContainingAndPhoneContaining(name, phone, pageable);
    }

//    public List<ContactDTO> findAll(){
//        return contactRepository
//                .findAll()
//                .stream()
//                .map(ContactEntity -> contactMapper.entityToDTO(ContactEntity))
//                .collect(Collectors.toList());
//    }

    public List<ContactEntity> findContacts(Integer id){
        return contactRepository.findByAddress_idLike(id);
    }

    public HashMap<String, String> add(ContactDTO contactDTO){
        ContactEntity contactEntity = contactRepository.findByName(contactDTO.getName());
        if(contactEntity != null){
            throw new ContactoExistenteException("El nombre ya existe");
        }
        ContactEntity contactEntity1 = contactRepository.findByPhone(contactDTO.getPhone());
        if(contactEntity1 != null){
            throw new ContactoExistenteException("El telefono ya existe");
        }
        return Optional
                .ofNullable(contactDTO)
                .map(dto -> contactMapper.DTOToEntity(dto))
                .map(entity -> contactRepository.save(entity))
                .map(entity -> contactMapper.entityToMsj(entity))
                .orElse(new HashMap<>());
    }

    public ContactEntity delete(Integer id){
        ContactEntity contact = contactRepository.findById(id).orElseThrow(() -> new ContactoInexistenteException("No se encontró el contacto"));
        contactRepository.delete(contact);
        return contact;
    }

    public ContactEntity update(ContactDTO contactDTO, Integer id){
        ContactEntity contact = contactRepository.findById(id).orElseThrow(() -> new ContactoInexistenteException("No se encontró el contacto"));
        contact.setName(contactDTO.getName());
        contact.setPhone(contactDTO.getPhone());
        contact.setAddress(contactDTO.getAddress());
        contactRepository.save(contact);
        return contact;
    }

}
