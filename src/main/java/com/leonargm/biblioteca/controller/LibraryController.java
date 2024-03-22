package com.leonargm.biblioteca.controller;

import com.leonargm.biblioteca.model.Book;
import com.leonargm.biblioteca.service.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private ILibraryService libraryService;

    @PostMapping(value = "/book/create")
    public ResponseEntity<Object> createBook(@RequestParam String title, @RequestParam String author, @RequestParam String isbn) {
        try {
            Book bk = new Book(title, author, isbn, true);
            return libraryService.createBook(bk);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el libro: " + e.getMessage());
        }
    }

    @GetMapping(value = "/book/list")
    public ResponseEntity<Object> listBooks() {
        try {
            return libraryService.listBooks();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los libros: " + e.getMessage());
        }
    }

    @GetMapping(value = "/book/search")
    public ResponseEntity<Object> searchBook(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String isbn) {
        try {
            Book bk = new Book(title, author, isbn, true);
            return libraryService.searchBook(bk);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el libro: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/book/remove")
    public ResponseEntity<Object> removeBook(@RequestParam String isbn) {
        try {
            return libraryService.removeBook(isbn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el libro: " + e.getMessage());
        }
    }
}