package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByGenre(Genre genre);


}
