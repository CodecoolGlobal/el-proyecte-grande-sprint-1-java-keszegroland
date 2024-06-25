package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.dao.model.MainPagePost;

import java.util.List;

public interface PostDAO {
    List<MainPagePost> getAllPosts();

    List<Post> getAllPostsByUserId(int userID);

    boolean createPost(NewPostDTO postDTO, int userID);
}
