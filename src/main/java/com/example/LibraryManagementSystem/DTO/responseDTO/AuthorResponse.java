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
@Builder
public class AuthorResponse {

    String authorName;

    String email;

    int age;

    Date LastActivity;

    List<String> booksList;

}
