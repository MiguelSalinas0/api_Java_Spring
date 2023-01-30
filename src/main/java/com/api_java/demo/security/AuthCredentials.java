package com.api_java.demo.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String password;
    private String username;
}
