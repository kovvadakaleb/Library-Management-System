package com.example.LibraryManagementSystem.Model;

import com.example.LibraryManagementSystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    int no_of_pages;

    @Enumerated(EnumType.STRING)
    Genre genre;

    int cost;

    @ManyToOne
    @JoinColumn

    Author author;
}
