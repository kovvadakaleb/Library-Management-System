package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.responseDTO.TransactionResponse;
import com.example.LibraryManagementSystem.Service.Impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;
    @PostMapping("/issue-book")
    public ResponseEntity issueBook(@RequestParam("bookID") int bookID, @RequestParam("StudentID") int studentID){
        try {
            TransactionResponse transactionResponse = transactionServiceImpl.issueBook(bookID, studentID);
            return new ResponseEntity(transactionResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/return-book/book-id/{bookID}/student-id/{studentID}")
    public ResponseEntity returnBook(@PathVariable int bookID,@PathVariable int studentID){
        try {
            TransactionResponse transactionResponse = transactionServiceImpl.returnBook(bookID, studentID);
            return new ResponseEntity(transactionResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
