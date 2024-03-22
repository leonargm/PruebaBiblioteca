package com.leonargm.biblioteca.dao;

import com.leonargm.biblioteca.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBookDao extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM Book", nativeQuery = true)
    List<Book> findAllBooks();

    @Query(value = "SELECT * FROM Book WHERE title = :title", nativeQuery = true)
    List<Book> findBooksByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM Book WHERE author = :author", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("author") String author);

    @Query(value = "SELECT * FROM Book WHERE isbn = :isbn", nativeQuery = true)
    Book findBookByIsbn(@Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Book(title, author, isbn, available) VALUES (:title, :author, :isbn, :available)", nativeQuery = true)
    void createBook(@Param("title") String title, @Param("author") String author, @Param("isbn") String isbn, @Param("available") boolean available);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Book WHERE isbn = :isbn", nativeQuery = true)
    void removeBookByIsbn(@Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query(value="UPDATE Book SET available = ?1 WHERE isbn = ?2", nativeQuery = true)
    void updateBookByIsbn(boolean available, String isbnBook);
}