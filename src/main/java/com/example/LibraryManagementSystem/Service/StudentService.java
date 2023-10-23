package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.requsetDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.LibraryCardResponse;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.Model.Librarycard;
import com.example.LibraryManagementSystem.Model.Student;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Librarycard librarycard = new Librarycard();
        librarycard.setCardNO(String.valueOf(UUID.randomUUID()));
        librarycard.setCardStatus(CardStatus.ACTIVE);

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setGender(studentRequest.getGender());
        student.setDepartment(studentRequest.getDepartment());

        librarycard.setStudent(student);

        student.setLibrarycard(librarycard);

        Student response = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(response.getName());
        studentResponse.setAge(response.getAge());
        studentResponse.setEmail(response.getEmail());
        studentResponse.setGender(response.getGender());
        studentResponse.setDepartment(response.getDepartment());

        LibraryCardResponse libraryCardResponse = new LibraryCardResponse();
        libraryCardResponse.setCardNo(response.getLibrarycard().getCardNO());
        libraryCardResponse.setCardStatus(response.getLibrarycard().getCardStatus());
        libraryCardResponse.setIssueDate(response.getLibrarycard().getIssueDate());

        studentResponse.setLibraryCardResponse(libraryCardResponse);
        studentResponse.setMessage("Student Added Successfully");
        return studentResponse;
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> response = studentRepository.findById(regNo);
        if(response.isPresent()){
            Student student = response.get();
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setAge(student.getAge());
            studentResponse.setName(student.getName());
            studentResponse.setGender(student.getGender());
            studentResponse.setEmail(student.getEmail());
            studentResponse.setDepartment(student.getDepartment());
            studentResponse.setMessage(studentResponse.getName()+" Details are Found");

            LibraryCardResponse libraryCardResponse = new LibraryCardResponse();
            libraryCardResponse.setIssueDate(student.getLibrarycard().getIssueDate());
            libraryCardResponse.setCardStatus(student.getLibrarycard().getCardStatus());
            libraryCardResponse.setCardNo(student.getLibrarycard().getCardNO());

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
       for(Student student : students){
          StudentResponse studentResponse = new StudentResponse();
          studentResponse.setName(student.getName());
          studentResponse.setAge(student.getAge());
          studentResponse.setEmail(student.getEmail());
          studentResponse.setGender(student.getGender());
          studentResponse.setMessage(studentResponse.getName()+" Details are Found");
          studentResponse.setDepartment(student.getDepartment());

          LibraryCardResponse libraryCardResponse = new LibraryCardResponse();
          libraryCardResponse.setCardStatus(student.getLibrarycard().getCardStatus());
          libraryCardResponse.setIssueDate(student.getLibrarycard().getIssueDate());
          libraryCardResponse.setCardNo(student.getLibrarycard().getCardNO());

          studentResponse.setLibraryCardResponse(libraryCardResponse);

          studentResponseList.add(studentResponse);
       }
       return studentResponseList;
    }

    public List<StudentResponse> getAllMaleStudents() {
        List<StudentResponse> studentResponseList = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        for(Student student : students){
               StudentResponse studentResponse = new StudentResponse();
               studentResponse.setAge(student.getAge());
               studentResponse.setName(student.getName());
               studentResponse.setGender(student.getGender());
               studentResponse.setMessage(studentResponse.getName()+" Details Found");
               studentResponse.setEmail(student.getEmail());
               studentResponse.setDepartment(student.getDepartment());

               LibraryCardResponse libraryCardResponse = new LibraryCardResponse();
               libraryCardResponse.setCardStatus(student.getLibrarycard().getCardStatus());
               libraryCardResponse.setIssueDate(student.getLibrarycard().getIssueDate());
               libraryCardResponse.setCardNo(student.getLibrarycard().getCardNO());

               studentResponse.setLibraryCardResponse(libraryCardResponse);

               studentResponseList.add(studentResponse);

        }
        return studentResponseList;
    }

    public StudentResponse updateAge(int regNo, int age) {
        Optional<Student> response = studentRepository.findById(regNo);
        if(response.isPresent()){
            Student student = response.get();
            student.setAge(age);

            Student student1 = studentRepository.save(student);

            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setAge(student1.getAge());
            studentResponse.setName(student1.getName());
            studentResponse.setGender(student1.getGender());
            studentResponse.setMessage(studentResponse.getName()+" Age Updated Successfully");
            studentResponse.setEmail(student1.getEmail());
            studentResponse.setDepartment(student1.getDepartment());

            LibraryCardResponse libraryCardResponse = new LibraryCardResponse();
            libraryCardResponse.setCardStatus(student1.getLibrarycard().getCardStatus());
            libraryCardResponse.setIssueDate(student1.getLibrarycard().getIssueDate());
            libraryCardResponse.setCardNo(student1.getLibrarycard().getCardNO());

            studentResponse.setLibraryCardResponse(libraryCardResponse);

            return studentResponse;
        }
       return null;
    }
}

