package com.codecool.backend.service;

import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.User;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::convertPostToDTO).collect(Collectors.toList());
    }

    public List<PostDTO> getPostsByUserPublicId(UUID userPublicId) {
        List<Post> postsByUserId = postRepository.findAllByUserPublicId(userPublicId);
        return postsByUserId.stream().map(this::convertPostToDTO).collect(Collectors.toList());
    }

    public boolean createNewPost(NewPostDTO newPostDTO, UUID userPublicId) {
        try {
            Post post = new Post();
            post.setUser(getUserByPublicId(userPublicId));
            post.setDescription(newPostDTO.description());
            post.setPicture(newPostDTO.picture());
            postRepository.save(post);
            return true;
        } catch (RuntimeException e) {
            logger.error("Error creating a new post: {} ", e.getMessage());
            return false;
        }
    }

    private User getUserByPublicId(UUID userPublicId) {
        return userRepository.findByPublicId(userPublicId);
    }

    private PostDTO convertPostToDTO(Post post) {
        return new PostDTO(post.getPublicId(), post.getUser().getUsername(), post.getDescription(), post.getPicture(), post.getCreationDate());
    }

}