package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.dao.model.Post;

import java.util.List;

public interface PostDAO {
    List<Post> getAllPostsByUserId(int userID);

    boolean createPost(NewPostDTO postDTO, int userID);
}
