package com.codecool.backend.dao;

import com.codecool.backend.dao.model.Post;

import java.util.List;

public interface PostDAO {
    public List<Post> getAllPostsByUserId(int userID);
}
