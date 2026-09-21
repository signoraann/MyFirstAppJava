package com.signoraann.javalearning.lesson26;

import java.sql.*;
import java.time.OffsetDateTime;
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
        String searchUserByNameSql = "SELECT id, username, email, age FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchUserByNameSql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new User(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getObject("age", Integer.class),
                            resultSet.getObject("created_at", OffsetDateTime.class)));
                }
            }
        }
        return Optional.empty();
    }

    public List<User> findUsersByPartOfUsername(String username) throws SQLException {
        String searchUserByPartOfUsernameSql =
                "SELECT id, username, email, age from users WHERE username ILIKE '%' || ? || '%'";
        List<User> foundUsers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchUserByPartOfUsernameSql)) {
            preparedStatement.setString(1, username);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    foundUsers.add(new User(
                            result.getLong("id"),
                            result.getString("username"),
                            result.getString("email"),
                            result.getObject("age", Integer.class),
                            result.getObject("created_at", OffsetDateTime.class)));
                }
            }
            return foundUsers;
        }
    }

    public int[] addUsersInDatabase(List<User> users) throws SQLException {
        String addUserSql = "INSERT INTO users(username, email, age) VALUES (?, ?, ?)";
        boolean autoCommit = connection.getAutoCommit();
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(addUserSql)) {
                for (User user : users) {
                    preparedStatement.setString(1, user.username());
                    preparedStatement.setString(2, user.email());
                    if (user.age() == null) {
                        preparedStatement.setNull(3, Types.INTEGER);
                    } else {
                        preparedStatement.setInt(3, user.age());
                    }
                    preparedStatement.addBatch();
                }
                int[] result = preparedStatement.executeBatch();
                connection.commit();
                return result;
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
}
