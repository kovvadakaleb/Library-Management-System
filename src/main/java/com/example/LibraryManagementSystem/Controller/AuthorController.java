package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Service.AuthorService;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
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
    AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody  Author author) throws AuthorNotFoundException {
        String response =  authorService.addAuthor(author);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
