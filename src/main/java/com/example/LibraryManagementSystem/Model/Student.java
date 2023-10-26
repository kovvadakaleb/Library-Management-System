package com.example.LibraryManagementSystem.Model;

import com.example.LibraryManagementSystem.Enum.Department;
import com.example.LibraryManagementSystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "StudentDetails")
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

    String name;

    int age;

    @Column(unique = true,nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    Department department;


    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    Librarycard librarycard;



}
