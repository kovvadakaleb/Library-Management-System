package com.example.LibraryManagementSystem.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String Message){
        super(Message);
    }
}
