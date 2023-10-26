package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.responseDTO.TransactionResponse;

public interface TransactionService {
    TransactionResponse issueBook(int bookID, int studentID);
    TransactionResponse returnBook(int bookID, int studentID);
}
