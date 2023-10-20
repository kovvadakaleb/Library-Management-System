package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Model.Book;
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
    public String addBook(Book book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()){
           throw new AuthorNotFoundException("Invalid ID");
        }
        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBooks().add(book);
        authorRepository.save(author);
        return "Book Added Successfully";
    }

    public String deleteBook(int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Invalid BookId!!!");
        }
        Book book = bookOptional.get();
        List<Book> books = book.getAuthor().getBooks();
        books.remove(book);
        bookRepository.deleteById(bookId);
        return "Book Deleted Successfully";
    }

    public List<String> allBooksOfSameGenre(Genre genre) {
        List<String> bookTitles = new ArrayList<>();
        List<Book> books = bookRepository.findByGenre(genre);
        for(Book book : books){
            bookTitles.add(book.getTitle());
        }
        return bookTitles;
    }

    public List<String> allBooksOfGenreAndGreaterThanCost500(Genre genre) {
        List<String> list = new ArrayList<>();
        List<Book> books = bookRepository.findByGenre(genre);
        for(Book book : books){
            if(book.getCost()>500){
                list.add(book.getTitle());
            }
        }
        return list;
    }

    public List<String> allBooksWithBetweenPages(int a, int b) {
        List<String> list = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for(Book book : books){
            if(book.getNo_of_pages()>a && book.getNo_of_pages()<b) {
                list.add(book.getTitle());
            }
        }
        return list;
    }

    public List<String> allAuthorsWithSameGenre(Genre genre) {
      List<String> list= new ArrayList<>();
      List<Book> books = bookRepository.findByGenre(genre);
      for(Book book : books){
          list.add(book.getAuthor().getAuthorName());
      }
      return list;
    }
}
