package com.api_java.demo.model.repositories;

import com.api_java.demo.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findOneByUsername(String username);
}
