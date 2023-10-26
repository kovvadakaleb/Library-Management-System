package com.example.LibraryManagementSystem.DTO.responseDTO;

import com.example.LibraryManagementSystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    String title;

    int no_of_pages;

    Genre genre;

    String availability;

   int cost;

   List<String> transactionList;

   String authorName;
}
