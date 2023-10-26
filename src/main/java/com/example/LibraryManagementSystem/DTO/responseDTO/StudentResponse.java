package com.example.LibraryManagementSystem.DTO.responseDTO;

import com.example.LibraryManagementSystem.Enum.Department;
import com.example.LibraryManagementSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {
    String name;

    int age;

    String email;

    Department department;

    Gender gender;

    LibraryCardResponse libraryCardResponse;

}
