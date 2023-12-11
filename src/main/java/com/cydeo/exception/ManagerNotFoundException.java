package com.cydeo.exception;

public class ManagerNotFoundException extends RuntimeException {

    public ManagerNotFoundException(String message) {
        super(message);
    }

}
