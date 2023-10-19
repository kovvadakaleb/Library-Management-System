package com.example.LibraryManagementSystem.exception;

public class AuthorNotFoundException extends RuntimeException{


    public AuthorNotFoundException(String Message) {
        super(Message);
    }
}
