package com.leonargm.biblioteca.dao;

import com.leonargm.biblioteca.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface IUserBookDao extends JpaRepository<Book, Integer> {
    @Modifying
    @Transactional
    @Query(value="INSERT INTO User_Book(code_user, isbn_book, available, created_date) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void checkOutBook(String codeUser, String isbnBook, boolean available, Timestamp createdDate);
    @Modifying
    @Transactional
    @Query(value="UPDATE User_Book SET available = ?1 WHERE code_user = ?2 and isbn_book= ?3 and available = false", nativeQuery = true)
    void returnBook(boolean available, String codeUser, String isbnBook);


}
