package com.example.LibraryManagementSystem.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorResponse {

    String authorName;

    String email;

    int age;

    String Message;

    Date LastActivity;

    List<BookResponse> booksList;

}
