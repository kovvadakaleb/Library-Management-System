package com.example.LibraryManagementSystem.DTO.requsetDTO;

import com.example.LibraryManagementSystem.DTO.responseDTO.BookResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorRequest {

    String authorName;

    String email;

    int age;

}
