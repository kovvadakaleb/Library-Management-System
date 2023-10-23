package com.example.LibraryManagementSystem.DTO.requsetDTO;

import com.example.LibraryManagementSystem.Enum.Department;
import com.example.LibraryManagementSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequest {
    String name;

    int age;

    String email;

    Department department;

    Gender gender;

}
