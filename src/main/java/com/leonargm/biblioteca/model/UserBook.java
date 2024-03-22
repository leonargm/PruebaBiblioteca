package com.leonargm.biblioteca.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codeUser")
    private String codeUser;
    @Column(name = "isbnBook")
    private String isbnBook;
    @Column(name = "available")
    private boolean available;
    @Column(name = "createdDate")
    private Timestamp createdDate;

    public UserBook(String codeUser, String isbnBook, boolean available, Timestamp createdDate) {
        this.codeUser = codeUser;
        this.isbnBook = isbnBook;
        this.available = available;
        this.createdDate = createdDate;
    }
    public UserBook() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(String codeUser) {
        this.codeUser = codeUser;
    }

    public String isIsbnBook() {
        return isbnBook;
    }

    public void setIsbnBook(String isbnBook) {
        this.isbnBook = isbnBook;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
