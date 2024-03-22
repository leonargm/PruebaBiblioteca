package com.leonargm.biblioteca.service;

import com.leonargm.biblioteca.dao.IBookDao;
import com.leonargm.biblioteca.dao.IUserBookDao;
import com.leonargm.biblioteca.dao.IUserDao;
import com.leonargm.biblioteca.model.Book;
import com.leonargm.biblioteca.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao repoUser;
    @Autowired
    private IBookDao repoBook;
    @Autowired
    private IUserBookDao repoUserBook;
    private static final String ERROR_MESSAGE = "Ocurrió un error";

    @Override
    public ResponseEntity<Object> checkOutBook(String codeUser, String isbnBook) {
        try {
            HashMap<String, Object> datos = new HashMap<>();
            Users us = repoUser.findUserByCode(codeUser);
            if(us==null){
                datos.put("data","--");
                datos.put("message", "El usuario no existe");
                return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
            }
            Book bk = repoBook.findBookByIsbn(isbnBook);
            if(bk==null){
                datos.put("data","--");
                datos.put("message", "El libro no existe");
                return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
            }else{
                if(!bk.isAvailable()){
                    datos.put("data","--");
                    datos.put("message", "El libro ya se encuentra prestado");
                    return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
                }
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            repoUserBook.checkOutBook(codeUser, isbnBook, false, timestamp);
            repoBook.updateBookByIsbn(false, isbnBook);
            datos.put("data","--");
            datos.put("message", "Se presto el libro con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleServiceError(e);
        }
    }

    @Override
    public ResponseEntity<Object> returnBook(String codeUser, String isbnBook) {
        try {
            HashMap<String, Object> datos = new HashMap<>();
            Users us = repoUser.findUserByCode(codeUser);
            if(us==null){
                datos.put("data","--");
                datos.put("message", "El usuario no existe");
                return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
            }
            Book bk = repoBook.findBookByIsbn(isbnBook);
            if(bk==null){
                datos.put("data","--");
                datos.put("message", "El libro no existe");
                return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
            }else{
                if(bk.isAvailable()){
                    datos.put("data","--");
                    datos.put("message", "El libro no se encuentra prestado");
                    return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
                }
            }
            repoUserBook.returnBook(true, codeUser, isbnBook);
            repoBook.updateBookByIsbn(true, isbnBook);
            datos.put("data","--");
            datos.put("message", "Se entrego el libro con éxito");
            return new ResponseEntity<>(datos, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleServiceError(e);
        }
    }

    private ResponseEntity<Object> handleServiceError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE + ": " + e.getMessage());
    }


}
