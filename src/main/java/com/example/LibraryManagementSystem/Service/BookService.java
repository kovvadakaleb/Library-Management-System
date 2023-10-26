package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.requsetDTO.BookRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Enum.Genre;

import java.util.List;

public interface BookService {
     BookResponse addBook(BookRequest bookRequest);
     String deleteBook(int bookId);
     List<BookResponse> allBooksOfSameGenre(Genre genre);
    List<BookResponse> allBooksOfGenreAndGreaterThanCost(Genre genre,int cost);
    List<BookResponse> allBooksWithBetweenPages(int a, int b);
    List<AuthorResponse> allAuthorsWithSameGenre(Genre genre);

}
