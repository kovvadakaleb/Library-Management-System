package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.requsetDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse addStudent(StudentRequest studentRequest);
    StudentResponse getStudent(int regNo);
    String deleteStudent(int regNo);
    List<StudentResponse> getAllStudent();
    List<StudentResponse> getAllMaleStudents();
    StudentResponse updateAge(int regNo, int age);
}
