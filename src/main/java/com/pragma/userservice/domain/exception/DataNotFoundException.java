package com.pragma.userservice.domain.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message){
        super(message);
    }

}
