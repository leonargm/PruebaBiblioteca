package com.leonargm.biblioteca.service;

import com.leonargm.biblioteca.model.Book;
import org.springframework.http.ResponseEntity;

public interface ILibraryService {
    ResponseEntity<Object> createBook(Book bk);
    ResponseEntity<Object> searchBook(Book bk);
    ResponseEntity<Object> listBooks();
    ResponseEntity<Object> removeBook(String isbn);
}
