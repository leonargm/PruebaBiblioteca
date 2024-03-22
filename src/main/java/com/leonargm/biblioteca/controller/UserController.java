package com.leonargm.biblioteca.controller;

import com.leonargm.biblioteca.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/library")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user/checkoutbook")
    public ResponseEntity<Object> checkOutBook(@RequestParam String codeUser, @RequestParam String isbnBook) {
        try {
            return userService.checkOutBook(codeUser, isbnBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al prestar el libro: " + e.getMessage());
        }
    }
    @PostMapping(value = "/user/returnbook")
    public ResponseEntity<Object> returnBook(@RequestParam String codeUser, @RequestParam String isbnBook) {
        try {
            return userService.returnBook(codeUser, isbnBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al entregar el libro: " + e.getMessage());
        }
    }
}