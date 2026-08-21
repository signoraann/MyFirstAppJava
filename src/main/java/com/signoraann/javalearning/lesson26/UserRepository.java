package com.signoraann.javalearning.lesson26;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<String> findAllUsers() throws SQLException {
        String sqlAllUsers = "SELECT username FROM users";
        List<String> usernames = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlAllUsers);
                ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                usernames.add(result.getString("username"));
            }
        }
        return usernames;
    }

    public String findUserByUsername(String username) throws SQLException {
        String sqlSearchUserByName = "SELECT username FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSearchUserByName)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                }
            }
        }
        return null;
    }
}
