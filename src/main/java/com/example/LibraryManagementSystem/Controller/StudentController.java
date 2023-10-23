package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.requsetDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.Model.Student;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
         StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
       StudentResponse response = studentService.getStudent(regNo);
       if(response!=null) {
           return new ResponseEntity(response,HttpStatus.CREATED);
       }
       return new ResponseEntity("InvalidRequest",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{regNo}")
    public String deleteStudent(@PathVariable int regNo){
       return studentService.deleteStudent(regNo);
    }

    @GetMapping("/get-all")
    public List<StudentResponse> getAllStudent(){
        return studentService.getAllStudent();
    }


    @GetMapping("/get-allmale")
    public List<StudentResponse> getAllMaleStudents(){
        return studentService.getAllMaleStudents();
    }

    @PutMapping("/update-age/{regNo}")
    public ResponseEntity updateAge(@PathVariable int regNo,@RequestParam("age") int age){
        StudentResponse studentResponse =  studentService.updateAge(regNo,age);
        if(studentResponse!=null){
            return new ResponseEntity(studentResponse,HttpStatus.CREATED);
        }
        return new ResponseEntity("Invalid regNo",HttpStatus.NOT_FOUND);
    }
}
