package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.DTO.responseDTO.LibraryCardResponse;
import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Model.Librarycard;
import com.example.LibraryManagementSystem.Model.Student;

import java.util.UUID;

public class LibraryCardTransformer {

    public static Librarycard PrepareCardForStudent(){
         return Librarycard.builder()
                .cardNO(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
    }

    public static LibraryCardResponse GetMyCardDetails(Student student){
         return LibraryCardResponse.builder()
                .cardNo(student.getLibrarycard().getCardNO())
                .cardStatus(student.getLibrarycard().getCardStatus())
                .issueDate(student.getLibrarycard().getIssueDate())
                .build();
    }
}
