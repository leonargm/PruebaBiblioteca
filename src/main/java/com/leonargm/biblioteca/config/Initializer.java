package com.leonargm.biblioteca.config;

import com.leonargm.biblioteca.dao.IBookDao;
import com.leonargm.biblioteca.dao.IUserDao;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private IBookDao repoBook;
    @Autowired
    private IUserDao repoUser;

    @PostConstruct
    public void init() {
        generateBooksAutomatically();
        generateUsersAutomatically();
    }

    private void generateBooksAutomatically() {
        try {
            for (int i = 0; i < 5; i++) {
                String title = "Libro " + (i + 1);
                String author = "Autor " + (i + 1);
                String isbn = "ISBN-" + (i + 1);
                repoBook.createBook(title, author, isbn, true);
            }
            System.out.println("Libros generados automáticamente al iniciar la aplicación.");
        } catch (Exception e) {
            System.err.println("Error al generar libros automáticamente: " + e.getMessage());
        }
    }
    private void generateUsersAutomatically() {
        try {
            for (int i = 0; i < 5; i++) {
                String name = "Usuario " + (i + 1);
                String code = "ID-" + (i + 1);
                repoUser.createUser(name, code, true);
            }
            System.out.println("Usuarios generados automáticamente al iniciar la aplicación.");
        } catch (Exception e) {
            System.err.println("Error al generar usuarios automáticamente: " + e.getMessage());
        }
    }


}
