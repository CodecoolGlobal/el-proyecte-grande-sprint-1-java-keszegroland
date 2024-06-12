package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.dao.PostDAO;
import com.codecool.backend.dao.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostDAO postDAO;

    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
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
        return new PostDTO(post.post_id(), post.user_id(), post.description(), post.picture(), post.creation_date());
    }

}
