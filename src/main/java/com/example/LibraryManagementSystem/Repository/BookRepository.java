package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByGenre(Genre genre);


    @Query(value = "select b from Book b where b.genre =:genre and b.cost >=:cost")
    List<Book> allBooksOfGenreAndGreaterThanCost500(Genre genre,int cost);

    @Query(value = "select c from Book c where c.no_of_pages >:a and c.no_of_pages <:b")
    List<Book> allBooksWithBetweenPages(int a, int b);
}
