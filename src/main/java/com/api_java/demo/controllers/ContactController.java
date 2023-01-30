package com.api_java.demo.controllers;

import com.api_java.demo.model.domain.ContactDTO;
import com.api_java.demo.model.entities.ContactEntity;
import com.api_java.demo.services.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/contacto")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContactEntity> getOne(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(contactService.getOne(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ContactEntity>> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(contactService.findAll(page, size));
    }

    @GetMapping("/allFilter")
    public ResponseEntity<Page<ContactEntity>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(contactService.findAllWithFilter(name, phone, page, size));
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<ContactDTO>> findAll(){
//        return ResponseEntity.ok(contactService.findAll());
//    }

    @PostMapping("/add")
    public ResponseEntity<HashMap<String, String>> add(@RequestBody ContactDTO contactDTO){
        HashMap<String, String> map = contactService.add(contactDTO);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/pop/{id}")
    public ResponseEntity<ContactEntity> delete(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(contactService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactEntity> update(@RequestBody ContactDTO contactDTO, @PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(contactService.update(contactDTO, id));
    }

}
