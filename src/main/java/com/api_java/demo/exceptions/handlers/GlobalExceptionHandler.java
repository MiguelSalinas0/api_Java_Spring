package com.api_java.demo.exceptions.handlers;

import com.api_java.demo.exceptions.dtos.ErrorMessageDTO;
import com.api_java.demo.exceptions.exceptionskinds.ContactoInexistenteException;
import com.api_java.demo.exceptions.exceptionskinds.ContactoExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> defaultErrorHandler(HttpServletRequest request, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContactoInexistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> notFoundExceptionsHandler(HttpServletRequest request, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactoExistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> existExceptionsHandler(HttpServletRequest request, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

}
