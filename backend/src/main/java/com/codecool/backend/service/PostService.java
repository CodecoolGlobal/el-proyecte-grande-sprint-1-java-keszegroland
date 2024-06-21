package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MainPostDTO;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.dao.PostDAO;
import com.codecool.backend.dao.model.MainPagePost;
import com.codecool.backend.dao.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<MainPostDTO> getAllPosts() {
        List<MainPagePost> posts = postDAO.getAllPosts();
        return posts.stream().map(this::convertMainPagePostToMainPostDTO).toList();
    }

    public List<PostDTO> getAllPostsByUserId(int userId) {
        List<Post> posts = postDAO.getAllPostsByUserId(userId);
        return posts.stream()
                .map(this::convertPostToPostDTO)
                .toList();
    }

    public boolean createNewPost(NewPostDTO postDTO, int userId) {
        return postDAO.createPost(postDTO, userId);
    }

    private PostDTO convertPostToPostDTO(Post post) {
        return new PostDTO(post.postId(), post.userId(), post.description(), post.picture(), post.creationDate());
    }

    private MainPostDTO convertMainPagePostToMainPostDTO(MainPagePost post) {
        return new MainPostDTO(post.username(), post.description(), post.picture(), post.creationDate());
    }

}
