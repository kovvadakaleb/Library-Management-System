package com.example.LibraryManagementSystem.Service.Impl;

import com.example.LibraryManagementSystem.DTO.responseDTO.TransactionResponse;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Model.Student;
import com.example.LibraryManagementSystem.Model.Transaction;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import com.example.LibraryManagementSystem.Service.TransactionService;
import com.example.LibraryManagementSystem.Transformer.TransactionTransformer;
import com.example.LibraryManagementSystem.exception.BookNotFoundException;
import com.example.LibraryManagementSystem.exception.IssueException;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;
    public TransactionResponse issueBook(int bookID, int studentID) {
        Optional<Student> studentOptional = studentRepository.findById(studentID);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid Student ID");
        }
        Student student = studentOptional.get();
        Optional<Book> bookOptional = bookRepository.findById(bookID);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Invalid Book ID");
        }

        Book book = bookOptional.get();
        if(book.getAvailability().equals("NO")){
            throw new IssueException("Book Already Issued");
        }
        book.setAvailability("NO");

        Transaction transaction = TransactionTransformer.MakeTransaction();
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setBook(book);
        transaction.setLibrarycard(student.getLibrarycard());

        Transaction savedTransaction = transactionRepository.save(transaction);

        book.getTransactions().add(savedTransaction);

        bookRepository.save(book);

        student.getLibrarycard().getTransactionList().add(savedTransaction);


        TransactionResponse transactionResponse = TransactionTransformer.TransactionToTransactionResponse(savedTransaction);

        return transactionResponse;
    }


    public TransactionResponse returnBook(int bookID, int studentID) {

        Optional<Student> studentOptional = studentRepository.findById(studentID);

        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid Student ID");
        }

        Student student = studentOptional.get();

        Optional<Book> bookOptional = bookRepository.findById(bookID);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Invalid Book ID");
        }

        Book book = bookOptional.get();
        if(book.getAvailability().equals("YES")){
            throw new IssueException("Book is Available or Already Returned");
        }
        boolean success = true;
        if(!TransactionTransformer.isBookTakenByStudentOrNot(book,student,transactionRepository)){
            success=false;
        }

        Transaction transaction = TransactionTransformer.MakeTransaction();
        transaction.setLibrarycard(student.getLibrarycard());
        transaction.setBook(book);

        if(success){
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            book.setAvailability("YES");
        }
        else{
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        book.getTransactions().add(savedTransaction);
        student.getLibrarycard().getTransactionList().add(savedTransaction);

        bookRepository.save(book);

        return TransactionTransformer.TransactionToTransactionResponse(savedTransaction);
    }
}
