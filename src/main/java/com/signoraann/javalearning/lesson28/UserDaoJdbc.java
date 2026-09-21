package com.signoraann.javalearning.lesson28;

import com.signoraann.javalearning.lesson26.User;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.Optional;

public class UserDaoJdbc implements UserDao {
    private final Connection connection;

    public UserDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getObject("age", Integer.class),
                resultSet.getObject("created_at", OffsetDateTime.class));
    }

    @Override
    public void save(User user) throws SQLException {
        String saveUserSql = "INSERT INTO users(username, email, age, created_at) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUserSql)) {
            preparedStatement.setString(1, user.username());
            preparedStatement.setString(2, user.email());
            if (user.age() == null) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, user.age());
            }
            preparedStatement.setObject(4, OffsetDateTime.now());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Optional<User> findUserByUsername(String username) throws SQLException {
        String findUserByUsernameSql = "SELECT id,username,email,age,created_at FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findUserByUsernameSql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) throws SQLException {
        String findUserById = "SELECT id,username,email,age, created_at FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(findUserById)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteByUsername(String username) throws SQLException {
        String deleteByUsernameSql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteByUsernameSql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }
}
