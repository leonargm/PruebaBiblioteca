package com.leonargm.biblioteca.service;

import com.leonargm.biblioteca.dao.IBookDao;
import com.leonargm.biblioteca.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LibraryService implements ILibraryService {

    @Autowired
    private IBookDao repoBook;

    private static final String SUCCESS_MESSAGE = "Operación exitosa";
    private static final String ERROR_MESSAGE = "Ocurrió un error";

    @Override
    public ResponseEntity<Object> createBook(Book bk) {
        try {
            Book existingBook = repoBook.findBookByIsbn(bk.getIsbn());
            if (existingBook != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El ISBN del libro ya está registrado");
            }
            repoBook.createBook(bk.getTitle(), bk.getAuthor(), bk.getIsbn(), true);
            return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_MESSAGE);
        } catch (Exception e) {
            return handleServiceError(e);
        }
    }

    @Override
    public ResponseEntity<Object> listBooks() {
        try {
            List<Book> books = repoBook.findAllBooks();
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            return handleServiceError(e);
        }
    }

    @Override
    public ResponseEntity<Object> searchBook(Book bk) {
        try {
            List<Book> books = new ArrayList<>();
            if (bk.getTitle() != null && !bk.getTitle().isEmpty()) {
                books.addAll(repoBook.findBooksByTitle(bk.getTitle()));
            }
            if (bk.getAuthor() != null && !bk.getAuthor().isEmpty()) {
                books.addAll(repoBook.findBooksByAuthor(bk.getAuthor()));
            }
            if (bk.getIsbn() != null && !bk.getIsbn().isEmpty()) {
                Book resultBook = repoBook.findBookByIsbn(bk.getIsbn());
                if (resultBook != null) {
                    books.add(resultBook);
                }
            }

            if (books.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron libros con los criterios de búsqueda");
            }

            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            return handleServiceError(e);
        }
    }

    @Override
    public ResponseEntity<Object> removeBook(String isbn) {
        try {
            Book bookToRemove = repoBook.findBookByIsbn(isbn);
            if (bookToRemove == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro que intenta eliminar no existe");
            }
            if (!bookToRemove.isAvailable()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El libro que intenta eliminar está prestado");
            }
            repoBook.removeBookByIsbn(isbn);
            return ResponseEntity.status(HttpStatus.OK).body(SUCCESS_MESSAGE);
        } catch (Exception e) {
            return handleServiceError(e);
        }
    }

    private ResponseEntity<Object> handleServiceError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE + ": " + e.getMessage());
    }
}
