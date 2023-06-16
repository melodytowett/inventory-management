package com.example.inventorymanagement.exception;

public class ItemNotFoundException extends RuntimeException{
    private String message;
    public ItemNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
