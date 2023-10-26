package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.requsetDTO.AuthorRequest;
import com.example.LibraryManagementSystem.DTO.responseDTO.AuthorResponse;

public interface AuthorService{
     AuthorResponse addAuthor(AuthorRequest authorRequest);
}
