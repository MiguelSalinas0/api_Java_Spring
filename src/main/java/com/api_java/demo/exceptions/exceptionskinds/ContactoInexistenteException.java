package com.api_java.demo.exceptions.exceptionskinds;

public class ContactoInexistenteException extends RuntimeException {

    public ContactoInexistenteException(String message) {
        super(message);
    }
}
