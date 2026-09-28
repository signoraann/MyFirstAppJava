package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.User;

import java.util.Optional;

public interface UserDao {
    void saveUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Long id);

    void deleteByUsername(String username);
}
