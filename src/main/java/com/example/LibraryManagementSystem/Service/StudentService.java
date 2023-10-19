package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.Model.Librarycard;
import com.example.LibraryManagementSystem.Model.Student;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) {
        Librarycard librarycard = new Librarycard();
        librarycard.setCardNO(String.valueOf(UUID.randomUUID()));
        librarycard.setCardStatus(CardStatus.ACTIVE);

        librarycard.setStudent(student);

       student.setLibrarycard(librarycard);

        Student response = studentRepository.save(student);

        return "Student added to DB";
    }

    public String getStudent(int regNo) {
        Optional<Student> response = studentRepository.findById(regNo);
        if(response.isPresent()){
            Student student = response.get();
            return student.getName();
        }
        return null;
    }

    public String deleteStudent(int regNo) {
        studentRepository.deleteById(regNo);
        return "Successfully deleted";
    }

    public List<String> getAllStudent() {
        List<String> list= new ArrayList<>();
       List<Student> students = studentRepository.findAll();
       for(Student student : students){
           list.add(student.getName());
       }
       return list;
    }

    public List<String> getAllMaleStudents() {
        List<String> list = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for(Student student : students){
            if(student.getGender().equals(Gender.MALE)){
                list.add(student.getName());
            }
        }
        return list;
    }

    public String updateAge(int regNo, int age) {
        Optional<Student> response = studentRepository.findById(regNo);
        if(response.isPresent()){
            Student student = response.get();
            student.setAge(age);
            studentRepository.save(student);
            return "updated Successfully";
        }
       return "Invalid ID!!";
    }
}

