package com.signoraann.javalearning.lesson26;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<String> findAllUsernames() throws SQLException {
        String selectUsernamesSql = "SELECT username FROM users ORDER BY username";
        List<String> usernames = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectUsernamesSql);
                ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                usernames.add(result.getString("username"));
            }
        }
        return usernames;
    }

    public Optional<User> findUserByUsername(String username) throws SQLException {
        String searchUserByNameSql = "SELECT id, username, email FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchUserByNameSql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new User(
                            resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("email")));
                }
            }
        }
        return Optional.empty();
    }
}
