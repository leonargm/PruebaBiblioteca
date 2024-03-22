package com.leonargm.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "available")
    private boolean available;

    public Users() {
    }

    public Users(String name, String code, boolean available) {
        this.name = name;
        this.code = code;
        this.available = available;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public String getAuthor() {
        return code;
    }

    public void setAuthor(String author) {
        this.code = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
