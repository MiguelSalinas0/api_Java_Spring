package com.api_java.demo.controllers;

import com.api_java.demo.model.entities.AddressEntity;
import com.api_java.demo.model.entities.ContactEntity;
import com.api_java.demo.services.AddressService;
import com.api_java.demo.services.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final ContactService contactService;

    public AddressController(AddressService addressService, ContactService contactService) {
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<AddressEntity>> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(addressService.findAll(page, size));
    }

    @GetMapping("/allFilter")
    public ResponseEntity<Page<AddressEntity>> findAll(
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String number,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(addressService.findAllWithFilter(street, number, page, size));
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<ContactEntity>> findContact(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(contactService.findContacts(id));
    }

}