package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {
    void saveUser(User user);

    List<User> findUsersByUsername(String username);

    Optional<User> findUserById(Long id);

    boolean deleteByEmail(String email);

    List<String> findAllUsernames();

    Map<String, List<String>> findAllUsersPosts();

    List<User> findAllUsers();
}
