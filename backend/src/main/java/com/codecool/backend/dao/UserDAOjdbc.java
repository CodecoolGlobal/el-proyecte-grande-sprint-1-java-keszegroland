package com.codecool.backend.dao;

import com.codecool.backend.configuration.DatabaseConnection;
import com.codecool.backend.dao.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class UserDAOjdbc implements UserDAO {
    private DatabaseConnection databaseConnection;

    public UserDAOjdbc(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try(Connection conn = databaseConnection.getConnection();
        ResultSet rs = conn.prepareStatement(sql).executeQuery()) {
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                user = new User(userId, firstName, lastName, username, password, email);
            }

        } catch (SQLException e) {
            System.out.println("Error while retrieving User with given id " + id + ", " + e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }
}
