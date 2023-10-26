package com.example.LibraryManagementSystem.Service.Impl;

import com.example.LibraryManagementSystem.DTO.requsetDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.LibraryCardResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.Model.Librarycard;
import com.example.LibraryManagementSystem.Model.Student;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.Service.StudentService;
import com.example.LibraryManagementSystem.Transformer.LibraryCardTransformer;
import com.example.LibraryManagementSystem.Transformer.StudentTransformer;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {

        Student student = StudentTransformer.StudentRequestToStudent(studentRequest);

        Librarycard librarycard = LibraryCardTransformer.PrepareCardForStudent();

        librarycard.setStudent(student);
        student.setLibrarycard(librarycard);

        Student savedStudent = studentRepository.save(student);

        StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(savedStudent);

        LibraryCardResponse libraryCardResponse = LibraryCardTransformer.GetMyCardDetails(savedStudent);

        studentResponse.setLibraryCardResponse(libraryCardResponse);

        return studentResponse;
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> response = studentRepository.findById(regNo);
        if (response.isPresent()) {
            Student student = response.get();

            StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(student);

            LibraryCardResponse libraryCardResponse = LibraryCardTransformer.GetMyCardDetails(student);

            studentResponse.setLibraryCardResponse(libraryCardResponse);

            return studentResponse;

        }
        return null;
    }

    public String deleteStudent(int regNo) {
        studentRepository.deleteById(regNo);
        return "Successfully deleted";
    }

    public List<StudentResponse> getAllStudent() {

        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (Student student : students) {
            StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(student);

            LibraryCardResponse libraryCardResponse = LibraryCardTransformer.GetMyCardDetails(student);

            studentResponse.setLibraryCardResponse(libraryCardResponse);

            studentResponseList.add(studentResponse);
        }
        return studentResponseList;
    }

    public List<StudentResponse> getAllMaleStudents() {
        List<StudentResponse> studentResponseList = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        for (Student student : students) {
            StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(student);

            LibraryCardResponse libraryCardResponse = LibraryCardTransformer.GetMyCardDetails(student);

            studentResponse.setLibraryCardResponse(libraryCardResponse);

            studentResponseList.add(studentResponse);

        }
        return studentResponseList;
    }

    public StudentResponse updateAge(int regNo, int age) {
        Optional<Student> response = studentRepository.findById(regNo);

        if (response.isEmpty()) {
            throw new StudentNotFoundException("Student Not Found");
        }
        Student student = response.get();
        student.setAge(age);

        Student updateStudent = studentRepository.save(student);

        StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(updateStudent);

        LibraryCardResponse libraryCardResponse = LibraryCardTransformer.GetMyCardDetails(updateStudent);

        studentResponse.setLibraryCardResponse(libraryCardResponse);

        return studentResponse;
    }

}




