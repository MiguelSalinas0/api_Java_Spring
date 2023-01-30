package com.api_java.demo.model.domain;

import com.api_java.demo.model.entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Integer id;
    private String name;
    private String phone;
    private AddressEntity address;
}
