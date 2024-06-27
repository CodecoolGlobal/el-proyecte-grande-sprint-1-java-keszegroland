package com.codecool.backend.service;

import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.User;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
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

    @Transactional
    public boolean createNewPost(NewPostDTO newPostDTO, UUID userPublicId) {
        try {
            Post post = new Post();
            post.setUser(getUserByPublicId(userPublicId));
            post.setDescription(newPostDTO.description());
            convertBase64Image(newPostDTO, post);
            postRepository.save(post);
            return true;
        } catch (RuntimeException e) {
            logger.error("Error creating a new post: {} ", e.getMessage());
            return false;
        }
    }

    private void convertBase64Image(NewPostDTO newPostDTO, Post post) {
        String pictureBase64Data = newPostDTO.picture();
        if (pictureBase64Data != null && !pictureBase64Data.isEmpty()) {
            byte[] data = Base64.getDecoder().decode(pictureBase64Data);
            post.setPicture(data);
        }
    }

    private String convertImageToBase64(byte[] picture) {
        String base64Image = null;
        if (picture != null) {
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(picture);
        }
        return base64Image;
    }

    private User getUserByPublicId(UUID userPublicId) {
        return userRepository.findByPublicId(userPublicId);
    }

    private PostDTO convertPostToDTO(Post post) {
        return new PostDTO(post.getPublicId(), post.getUser().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate());
    }

}