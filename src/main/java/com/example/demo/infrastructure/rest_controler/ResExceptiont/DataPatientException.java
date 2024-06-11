package com.example.demo.infrastructure.rest_controler.ResExceptiont;

import org.springframework.context.ApplicationContextException;

public class DataPatientException extends ApplicationContextException {
    public DataPatientException(String msg) {
        super(msg);
    }

    public DataPatientException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
