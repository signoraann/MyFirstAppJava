package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao {
    void save(User user) throws SQLException;

    Optional<User> findUserByUsername(String username) throws SQLException;

    Optional<User> findUserById(Long id) throws SQLException;

    void deleteByUsername(String username) throws SQLException;
}
