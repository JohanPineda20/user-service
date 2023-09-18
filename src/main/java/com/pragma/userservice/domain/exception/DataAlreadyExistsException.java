package com.pragma.userservice.domain.exception;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message){
        super(message);
    }
}
