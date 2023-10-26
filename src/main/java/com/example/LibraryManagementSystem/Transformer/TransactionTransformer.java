package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.DTO.responseDTO.TransactionResponse;
import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Model.Student;
import com.example.LibraryManagementSystem.Model.Transaction;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TransactionTransformer {



    public static TransactionResponse TransactionToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionNo(transaction.getTransactionNo())
                .transactionStatus(transaction.getTransactionStatus())
                .studentName(transaction.getLibrarycard().getStudent().getName())
                .cardNo(transaction.getLibrarycard().getCardNO())
                .authorName(transaction.getBook().getAuthor().getAuthorName())
                .bookName(transaction.getBook().getTitle())
                .build();
    }

    public static Transaction MakeTransaction() {
        return Transaction.builder()
                .transactionNo(String.valueOf(UUID.randomUUID()))
                .build();
    }

    public static boolean isBookTakenByStudentOrNot(Book book, Student student,TransactionRepository transactionRepository) {
        List<Transaction> transactionList = transactionRepository.findAll();
        int size = transactionList.size();
        for (int i = size - 1; i >= 0; i--) {
            Transaction transaction = transactionList.get(i);
            if (transaction.getBook().getId() == book.getId() && transaction.getTransactionStatus().equals(TransactionStatus.SUCCESS)) {

                if (transaction.getLibrarycard().getStudent().getRegNo() != student.getRegNo()) {
                        return false;
                }
                else{
                    break;
                }
            }
        }
        return true;
    }
}
