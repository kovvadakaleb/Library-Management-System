package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.DTO.requsetDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.Model.Student;

public class StudentTransformer {

    public static Student StudentRequestToStudent(StudentRequest studentRequest){
                return Student.builder()
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .name(studentRequest.getName())
                .department(studentRequest.getDepartment())
                .gender(studentRequest.getGender())
                .build();
    }

    public static StudentResponse StudentToStudentResponse(Student student){
        return StudentResponse.builder()
                .age(student.getAge())
                .name(student.getName())
                .department(student.getDepartment())
                .email(student.getEmail())
                .gender(student.getGender())
                .build();
    }

}
