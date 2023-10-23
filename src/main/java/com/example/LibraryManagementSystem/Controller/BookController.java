package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.requsetDTO.BookRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Service.BookService;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest) {
        try {
            BookResponse bookResponse = bookService.addBook(bookRequest);
            return new ResponseEntity(bookResponse, HttpStatus.CREATED);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable int bookId) {
        try {
            String response = bookService.deleteBook(bookId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (BookNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-Books-SameGenre/{genre}")
    public ResponseEntity allBooksOfSameGenre(@PathVariable Genre genre){
        List<BookResponse> response = bookService.allBooksOfSameGenre(genre);
        if(response.size()==0) {
            return new ResponseEntity("No Books are Found for Particular Genre",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @GetMapping("/get-books-genre&>500cost")
    public ResponseEntity allBooksOfGenreAndGreaterThanCost500(@RequestParam("genre") Genre genre,@RequestParam("cost") int cost){
        List<BookResponse> bookResponseList = bookService.allBooksOfGenreAndGreaterThanCost500(genre,cost);
        if(bookResponseList.size()==0){
            return new ResponseEntity("No Books Found For this genre and cost more than 500",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookResponseList,HttpStatus.CREATED);
    }

    @GetMapping("/get-books-betweenPages")
    public ResponseEntity allBooksWithBetweenPages(@RequestParam("page1")int a,@RequestParam("page2") int b){
        List<BookResponse> bookResponseList = bookService.allBooksWithBetweenPages(a,b);
        if(bookResponseList.size()==0){
            return new ResponseEntity("No Books Found Having these Pages",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookResponseList,HttpStatus.CREATED);
    }

    @GetMapping("/get-authors-sameGenre/{genre}")
    public ResponseEntity allAuthorsWithSameGenre(@PathVariable Genre genre){
        List<AuthorResponse> authorResponses= bookService.allAuthorsWithSameGenre(genre);
        if(authorResponses.size()==0){
            return new ResponseEntity("No Authors Found for Given Genre",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(authorResponses,HttpStatus.CREATED);
    }
}


