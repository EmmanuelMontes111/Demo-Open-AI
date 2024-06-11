package com.example.demo.infrastructure.rest_controler;

import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.infrastructure.rest_controler.ResExceptiont.DataPatientException;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = DataPatientException.class)
    public ResponseEntity<ErrorDto> dataClientExceptionHandler(DataPatientException ex) {
        ErrorDto errorDto = ErrorDto.builder().code("400").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PersistenceException.class)
    public ResponseEntity<ErrorDto> persistenceExceptionHandler(RuntimeException ex) {
        ErrorDto errorDto = ErrorDto.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}