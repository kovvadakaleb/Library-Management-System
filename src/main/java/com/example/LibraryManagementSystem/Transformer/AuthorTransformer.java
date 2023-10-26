package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.DTO.requsetDTO.AuthorRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorTransformer {

    public static Author AuthorRequestToAuthor(AuthorRequest authorRequest){

     return Author.builder()
             .age(authorRequest.getAge())
             .authorName(authorRequest.getAuthorName())
             .email(authorRequest.getEmail())
             .build();
    }

    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .authorName(author.getAuthorName())
                .age(author.getAge())
                .email(author.getEmail())
                .LastActivity(author.getLastActivity())
                .build();
    }

    public static List<String> getMyBooks(Author author){
        List<String> allBooks = new ArrayList<>();
        List<Book> books = author.getBooks();
        for(Book book : books){
            allBooks.add(book.getTitle());
        }
        return allBooks;
    }
}
