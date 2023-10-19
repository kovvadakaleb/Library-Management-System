package com.example.LibraryManagementSystem.Controller;

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
    public ResponseEntity addStudent(@RequestBody Student student){
         String response = studentService.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
       String response = studentService.getStudent(regNo);
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
    public List<String> getAllStudent(){
        return studentService.getAllStudent();
    }


    @GetMapping("/get-allmale")
    public List<String> getAllMaleStudents(){
        return studentService.getAllMaleStudents();
    }

    @PutMapping("/update-age/{regNo}")
    public String updateAge(@PathVariable int regNo,@RequestParam("age") int age){
        return studentService.updateAge(regNo,age);
    }
}
