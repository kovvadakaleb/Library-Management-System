package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.requsetDTO.BookRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Service.Impl.BookServiceImpl;
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
    BookServiceImpl bookServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest) {
        try {
            BookResponse bookResponse = bookServiceImpl.addBook(bookRequest);
            return new ResponseEntity(bookResponse, HttpStatus.CREATED);

        } catch (AuthorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable int bookId) {
        try {
            String response = bookServiceImpl.deleteBook(bookId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (BookNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-Books-SameGenre/{genre}")
    public ResponseEntity allBooksOfSameGenre(@PathVariable Genre genre){
        List<BookResponse> response = bookServiceImpl.allBooksOfSameGenre(genre);
        if(response.size()==0) {
            return new ResponseEntity("No Books are Found for Particular Genre",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @GetMapping("/get-books-genre&>500cost")
    public ResponseEntity allBooksOfGenreAndGreaterThanCost(@RequestParam("genre") Genre genre,@RequestParam("cost") int cost){
        List<BookResponse> bookResponseList = bookServiceImpl.allBooksOfGenreAndGreaterThanCost(genre,cost);
        if(bookResponseList.size()==0){
            return new ResponseEntity("No Books Found For this genre and greater cost ",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookResponseList,HttpStatus.CREATED);
    }

    @GetMapping("/get-books-betweenPages")
    public ResponseEntity allBooksWithBetweenPages(@RequestParam("page1")int a,@RequestParam("page2") int b){
        List<BookResponse> bookResponseList = bookServiceImpl.allBooksWithBetweenPages(a,b);
        if(bookResponseList.size()==0){
            return new ResponseEntity("No Books Found Having these Pages",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookResponseList,HttpStatus.CREATED);
    }

    @GetMapping("/get-authors-sameGenre/{genre}")
    public ResponseEntity allAuthorsWithSameGenre(@PathVariable Genre genre){
        List<AuthorResponse> authorResponses= bookServiceImpl.allAuthorsWithSameGenre(genre);
        if(authorResponses.size()==0){
            return new ResponseEntity("No Authors Found for Given Genre",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(authorResponses,HttpStatus.CREATED);
    }
}


