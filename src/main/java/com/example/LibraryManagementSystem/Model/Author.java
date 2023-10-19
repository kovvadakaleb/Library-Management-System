package com.example.LibraryManagementSystem.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String authorName;

    @Column(unique = true,nullable = false)
    String email;

    int age;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();

}
