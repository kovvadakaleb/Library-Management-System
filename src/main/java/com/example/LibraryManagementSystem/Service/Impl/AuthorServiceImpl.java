package com.example.LibraryManagementSystem.Service.Impl;

import com.example.LibraryManagementSystem.DTO.requsetDTO.AuthorRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.Model.Author;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Service.AuthorService;
import com.example.LibraryManagementSystem.Transformer.AuthorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = AuthorTransformer.AuthorRequestToAuthor(authorRequest);

        Author savedAuthor =  authorRepository.save(author);

        AuthorResponse authorResponse = AuthorTransformer.AuthorToAuthorResponse(savedAuthor);

        authorResponse.setBooksList(new ArrayList<>());

        return authorResponse;
    }
}
