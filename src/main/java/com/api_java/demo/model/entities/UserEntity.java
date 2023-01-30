package com.api_java.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)")
    public String username;

    @Column(name = "password", columnDefinition = "VARCHAR(200)")
    public String password;

}
