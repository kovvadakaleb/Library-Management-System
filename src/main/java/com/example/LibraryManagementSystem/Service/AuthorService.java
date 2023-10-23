package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.requsetDTO.AuthorRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setAuthorName(authorRequest.getAuthorName());
        author.setEmail(authorRequest.getEmail());
        author.setAge(authorRequest.getAge());

       Author author1 =  authorRepository.save(author);

        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setAge(author1.getAge());
        authorResponse.setAuthorName(author1.getAuthorName());
        authorResponse.setEmail(author1.getEmail());
        authorResponse.setLastActivity(author1.getLastActivity());
        authorResponse.setBooksList(new ArrayList<>());
        authorResponse.setMessage("Author Added Successfully");

     return authorResponse;
    }
}
