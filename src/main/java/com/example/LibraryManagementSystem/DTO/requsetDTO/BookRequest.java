package com.example.LibraryManagementSystem.DTO.requsetDTO;

import com.example.LibraryManagementSystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    String title;

    int no_of_pages;

    Genre genre;

    int cost;

    Integer authorId;
}
