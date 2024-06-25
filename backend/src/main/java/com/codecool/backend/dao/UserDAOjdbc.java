package com.codecool.backend.dao;

import com.codecool.backend.configuration.DatabaseConnection;
import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.controller.dto.UserLoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOjdbc implements UserDAO {
    private DatabaseConnection databaseConnection;

    public UserDAOjdbc(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = " + id;
        User user = null;
        try (Connection conn = databaseConnection.getConnection(); ResultSet rs = conn.prepareStatement(sql).executeQuery()) {
            user = getUser(user, rs);

        } catch (SQLException e) {
            System.out.println("Error while retrieving User with given id " + id + ", " + e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean createNewUser(NewUserDTO user) {
        String sql = "insert into users(first_name, last_name, username, password, email) values(?,?,?,?,?)";
        try (Connection connection = databaseConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.firstName());
            ps.setString(2, user.lastName());
            ps.setString(3, user.username());
            ps.setString(4, user.password());
            ps.setString(5, user.email());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error during create a new user. " + e.getMessage());
        }
        return false;
    }

    @Override
    public User loginUser(UserLoginDTO login) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;
        try (Connection connection = databaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, login.username());
            ps.setString(2, login.password());

            try (ResultSet rs = ps.executeQuery()){
                user = getUser(user, rs);
            }
        } catch (SQLException e) {
            System.out.println("Error during login user. " + e.getMessage());
        }
        return user;
    }

    private User getUser(User user, ResultSet rs) throws SQLException {
        if (rs.next()){
            int userId = rs.getInt("user_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            user = new User(userId, firstName, lastName, username, password, email);
        }
        return user;
    }
}
