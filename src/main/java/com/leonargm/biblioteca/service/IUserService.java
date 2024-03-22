package com.leonargm.biblioteca.service;

import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<Object> checkOutBook(String codeUser, String isbnBook);
    ResponseEntity<Object> returnBook(String codeUser, String isbnBook);
}
