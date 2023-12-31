package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.requsetDTO.StudentRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.Service.Impl.StudentServiceImpl;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
         StudentResponse response = studentServiceImpl.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
       StudentResponse response = studentServiceImpl.getStudent(regNo);
       if(response!=null) {
           return new ResponseEntity(response,HttpStatus.CREATED);
       }
       return new ResponseEntity("InvalidRequest",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{regNo}")
    public String deleteStudent(@PathVariable int regNo){
       return studentServiceImpl.deleteStudent(regNo);
    }

    @GetMapping("/get-all")
    public List<StudentResponse> getAllStudent(){
        return studentServiceImpl.getAllStudent();
    }


    @GetMapping("/get-allmale")
    public List<StudentResponse> getAllMaleStudents(){
        return studentServiceImpl.getAllMaleStudents();
    }

    @PutMapping("/update-age/{regNo}")
    public ResponseEntity updateAge(@PathVariable int regNo,@RequestParam("age") int age){

            try {
                StudentResponse studentResponse = studentServiceImpl.updateAge(regNo, age);
                return new ResponseEntity(studentResponse, HttpStatus.CREATED);
            }
          catch (StudentNotFoundException e) {
              return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
          }
    }
}
