package com.spring.practice.exception;

import lombok.NoArgsConstructor;



@NoArgsConstructor
public class ResourceNotFoundException extends Exception {


    public ResourceNotFoundException(String message){
        super(message);
    }
}
