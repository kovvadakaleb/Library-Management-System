package com.example.LibraryManagementSystem.Service.Impl;

import com.example.LibraryManagementSystem.DTO.requsetDTO.BookRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Service.BookService;
import com.example.LibraryManagementSystem.Transformer.AuthorTransformer;
import com.example.LibraryManagementSystem.Transformer.BookTransformer;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public BookResponse addBook(BookRequest bookRequest) {

        Optional<Author> authorOptional = authorRepository.findById(bookRequest.getAuthorId());
        if(authorOptional.isEmpty()){
           throw new AuthorNotFoundException("No Author Found");
        }
        Author author = authorOptional.get();

        Book book = BookTransformer.BookRequestToBook(bookRequest);
        book.setTransactions(new ArrayList<>());
        book.setAvailability("YES");
        book.setAuthor(author);
        author.getBooks().add(book);

       Author savedauthor = authorRepository.save(author);

       BookResponse bookResponse = BookTransformer.BookToBookResponse(book);

       bookResponse.setTransactionList(new ArrayList<>());

        return bookResponse;
    }

    public String deleteBook(int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Book Not Found");
        }

        Book book = bookOptional.get();
        List<Book> books = book.getAuthor().getBooks();
        books.remove(book);
        bookRepository.deleteById(bookId);
        return "Book Deleted Successfully";
    }

    public List<BookResponse> allBooksOfSameGenre(Genre genre) {
        List<BookResponse> bookResponseList = new ArrayList<>();
        List<Book> books = bookRepository.findByGenre(genre);
        for(Book book : books){
           BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
           List<String> transactionList = BookTransformer.getallTransactions(book);
           bookResponse.setTransactionList(transactionList);

           bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> allBooksOfGenreAndGreaterThanCost(Genre genre,int cost) {
        List<BookResponse> list = new ArrayList<>();
        List<Book> books = bookRepository.allBooksOfGenreAndGreaterThanCost500(genre,cost);
        for(Book book : books){
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
            List<String> transactionList = BookTransformer.getallTransactions(book);
            bookResponse.setTransactionList(transactionList);


            list.add(bookResponse);
        }
        return list;
    }

    public List<BookResponse> allBooksWithBetweenPages(int a, int b) {
        List<BookResponse> list = new ArrayList<>();
        List<Book> books = bookRepository.allBooksWithBetweenPages(a,b);
        for(Book book : books){
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
            List<String> transactionList = BookTransformer.getallTransactions(book);
            bookResponse.setTransactionList(transactionList);


            list.add(bookResponse);
        }
        return list;
    }

    public List<AuthorResponse> allAuthorsWithSameGenre(Genre genre) {
      List<AuthorResponse> list = new ArrayList<>();
      List<Book> books = bookRepository.findByGenre(genre);
      for(Book book : books){
          Author author = book.getAuthor();
          AuthorResponse authorResponse = AuthorTransformer.AuthorToAuthorResponse(author);
          List<String> booksList = AuthorTransformer.getMyBooks(author);
          authorResponse.setBooksList(booksList);
          if(!list.contains(authorResponse)){
              list.add(authorResponse);
          }
      }

      return list;
    }
}
