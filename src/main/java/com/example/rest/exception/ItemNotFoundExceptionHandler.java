package com.example.rest.exception;

public class ItemNotFoundExceptionHandler extends RuntimeException{
    public ItemNotFoundExceptionHandler(String message) {
        super(message);
    }
}
