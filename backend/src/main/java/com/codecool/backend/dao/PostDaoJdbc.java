package com.codecool.backend.dao;

import com.codecool.backend.configuration.DatabaseConnection;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.dao.model.MainPagePost;
import com.codecool.backend.dao.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDaoJdbc implements PostDAO {
    private final DatabaseConnection databaseConnection;

    public PostDaoJdbc(DatabaseConnection connection) {
        this.databaseConnection = connection;
    }

    @Override
    public List<MainPagePost> getAllPosts() {
        List<MainPagePost> posts = new ArrayList<>();
        String sql = "select users.username, posts.description, posts.picture, posts.creation_date\n" +
                "from posts\n" +
                "join users\n" +
                "on posts.user_id = users.user_id";
        try (Connection connection = databaseConnection.getConnection();
        ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String description = rs.getString("description");
                String picture = rs.getString("picture");
                Timestamp date = rs.getTimestamp("creation_date");
                LocalDateTime convertedDate = date.toLocalDateTime();
                MainPagePost post = new MainPagePost(username, description, picture, convertedDate);
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    @Override
    public List<Post> getAllPostsByUserId(int userID) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = " + userID;
        try (Connection connection = databaseConnection.getConnection();
             ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                int postId = rs.getInt("post_id");
                int userId = rs.getInt("user_id");
                String description = rs.getString("description");
                String picture = rs.getString("picture");
                Timestamp date = rs.getTimestamp("creation_date");
                LocalDateTime convertedDate = date.toLocalDateTime();
                Post post = new Post(postId, userId, description, picture, convertedDate);
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }

    @Override
    public boolean createPost(NewPostDTO postDTO, int userID) {
        String sql = "INSERT INTO posts (user_id, description, picture) VALUES (?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, userID);
            ps.setString(2, postDTO.description());
            ps.setString(3, postDTO.picture());
            ps.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println("Error during creation of new post. " + e.getMessage());
        }
        return false;
    }
}
