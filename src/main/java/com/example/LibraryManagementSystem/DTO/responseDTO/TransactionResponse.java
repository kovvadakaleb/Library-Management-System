package com.example.LibraryManagementSystem.DTO.responseDTO;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {

    String transactionNo;

    TransactionStatus transactionStatus;

    String studentName;

    String cardNo;

    String bookName;

    String authorName;

}
