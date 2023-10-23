package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.requsetDTO.BookRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Model.Book;
import com.example.LibraryManagementSystem.Model.Transaction;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

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

        Book book = new Book();
        book.setCost(bookRequest.getCost());
        book.setGenre(bookRequest.getGenre());
        book.setTitle(bookRequest.getTitle());
        book.setTransactions(new ArrayList<>());
        book.setAvailbility("YES");
        book.setNo_of_pages(bookRequest.getNo_of_pages());
        book.setAuthor(author);

        author.getBooks().add(book);

       Author author1 = authorRepository.save(author);

       BookResponse bookResponse = new BookResponse();
       bookResponse.setCost(book.getCost());
       bookResponse.setTitle(book.getTitle());
       bookResponse.setGenre(book.getGenre());
       bookResponse.setAvailbility(book.getAvailbility());
       bookResponse.setNo_of_pages(book.getNo_of_pages());
       bookResponse.setAuthorName(book.getAuthor().getAuthorName());
       bookResponse.setMessage("Book Added Successfully");
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
           BookResponse bookResponse = new BookResponse();
            bookResponse.setCost(book.getCost());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setAvailbility(book.getAvailbility());
            bookResponse.setNo_of_pages(book.getNo_of_pages());
            bookResponse.setAuthorName(book.getAuthor().getAuthorName());
            bookResponse.setMessage(book.getTitle()+" Book Details Found");

            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> allBooksOfGenreAndGreaterThanCost500(Genre genre,int cost) {
        List<BookResponse> list = new ArrayList<>();
        List<Book> books = bookRepository.allBooksOfGenreAndGreaterThanCost500(genre,cost);
        for(Book book : books){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setCost(book.getCost());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setAvailbility(book.getAvailbility());
            bookResponse.setNo_of_pages(book.getNo_of_pages());
            bookResponse.setAuthorName(book.getAuthor().getAuthorName());
            bookResponse.setMessage(book.getTitle()+" with Genre and Cost Found");

            list.add(bookResponse);
        }
        return list;
    }

    public List<BookResponse> allBooksWithBetweenPages(int a, int b) {
        List<BookResponse> list = new ArrayList<>();
        List<Book> books = bookRepository.allBooksWithBetweenPages(a,b);
        for(Book book : books){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setCost(book.getCost());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setAvailbility(book.getAvailbility());
            bookResponse.setNo_of_pages(book.getNo_of_pages());
            bookResponse.setAuthorName(book.getAuthor().getAuthorName());
            bookResponse.setMessage(book.getTitle()+" with Given Pages Found");

            list.add(bookResponse);
        }
        return list;
    }

    public List<AuthorResponse> allAuthorsWithSameGenre(Genre genre) {
      List<AuthorResponse> list = new ArrayList<>();
      List<Book> books = bookRepository.findByGenre(genre);
      for(Book book : books) {
          AuthorResponse authorResponse = new AuthorResponse();
          Author author = book.getAuthor();
          authorResponse.setAge(author.getAge());
          authorResponse.setAuthorName(author.getAuthorName());
          authorResponse.setEmail(author.getEmail());
          authorResponse.setLastActivity(author.getLastActivity());
          authorResponse.setMessage(book.getTitle()+" with Genre Found");

          list.add(authorResponse);
      }

      return list;
    }
}
