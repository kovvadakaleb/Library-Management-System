package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.requsetDTO.AuthorRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.Service.Impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Author")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorServiceImpl;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest) {
        AuthorResponse authorResponse =  authorServiceImpl.addAuthor(authorRequest);
        return new ResponseEntity(authorResponse, HttpStatus.CREATED);
    }
}
