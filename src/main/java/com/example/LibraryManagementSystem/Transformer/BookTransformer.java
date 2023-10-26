package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.DTO.requsetDTO.BookRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookTransformer {

    public static Book BookRequestToBook(BookRequest bookRequest){
        return Book.builder()
                .cost(bookRequest.getCost())
                .title(bookRequest.getTitle())
                .genre(bookRequest.getGenre())
                .no_of_pages(bookRequest.getNo_of_pages())
                .build();
    }

    public static BookResponse BookToBookResponse(Book book){
        return BookResponse.builder()
                .cost(book.getCost())
                .title(book.getTitle())
                .availability(book.getAvailability())
                .genre(book.getGenre())
                .no_of_pages(book.getNo_of_pages())
                .authorName(book.getAuthor().getAuthorName())
                .build();
    }

    public static List<String> getallTransactions(Book book){
        List<String> mylist =new ArrayList<>();
        List<Transaction> transactionList = book.getTransactions();
        for(Transaction transaction : transactionList){
            mylist.add(transaction.getTransactionNo());
        }
        return mylist;
    }
}
