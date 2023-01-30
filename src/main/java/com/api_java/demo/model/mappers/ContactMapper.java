package com.api_java.demo.model.mappers;

import com.api_java.demo.model.domain.ContactDTO;
import com.api_java.demo.model.entities.AddressEntity;
import com.api_java.demo.model.entities.ContactEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class ContactMapper {

    public ContactEntity DTOToEntity(ContactDTO contactDTO){
        ContactEntity contactEntity = new ContactEntity();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(contactDTO.getAddress().getId());
        addressEntity.setNumber(contactDTO.getAddress().getNumber());
        addressEntity.setStreet(contactDTO.getAddress().getStreet());
        contactEntity.setName(contactDTO.getName());

        contactEntity.setPhone(contactDTO.getPhone());
        contactEntity.setId(contactDTO.getId());
        contactEntity.setAddress(addressEntity);
        return contactEntity;
    }

    public ContactDTO entityToDTO(ContactEntity contactEntity){
        return Optional
                .ofNullable(contactEntity)
                .map(entity -> new ContactDTO(entity.getId(), entity.getName(), entity.getPhone(), entity.getAddress()))
                .orElse(new ContactDTO());
    }

    public HashMap<String, String> entityToMsj(ContactEntity entity){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", entity.getId().toString());
        map.put("mensaje", "Se agregó exitosamente");
        return map;
    }

}
