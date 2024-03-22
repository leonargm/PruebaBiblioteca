package com.leonargm.biblioteca.dao;

import com.leonargm.biblioteca.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<Users, Integer> {
    @Modifying
    @Transactional
    @Query(value="INSERT INTO Users(name, code, available) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void createUser(String name, String code, boolean available);

    @Query(value = "SELECT * FROM Users WHERE code = :code", nativeQuery = true)
    Users findUserByCode(@Param("code") String code);
}
