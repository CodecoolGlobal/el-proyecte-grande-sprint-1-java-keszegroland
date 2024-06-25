package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewCommentDTO;
import com.codecool.backend.model.Comment;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.User;
import com.codecool.backend.repository.CommentRepository;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentService {
    private final RestTemplate restTemplate;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(RestTemplate restTemplate, CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public UUID createComment(NewCommentDTO commentDTO, UUID userId, UUID postId) {
        Comment newComment = new Comment();
        newComment.setComment(commentDTO.comment());
        newComment.setUser(getUserForComment(userId));
        newComment.setPost(getPostForComment(postId));
        newComment.setCreationDate(LocalDateTime.now());
        commentRepository.save(newComment);
        return newComment.getCommentPublicId();
    }

    private User getUserForComment(UUID userId) {
        return userRepository.findByPublicId(userId);
    }

    private Post getPostForComment(UUID postId) {
        return postRepository.findByPublicId(postId);
    }
}
