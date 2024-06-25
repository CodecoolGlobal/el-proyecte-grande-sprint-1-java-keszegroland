package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MainPostDTO;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final RestTemplate restTemplate;
    private final PostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }

    public List<MainPostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        MainPostDTO[] result = restTemplate.getForObject("http://localhost:8080/api/posts", MainPostDTO[].class);
        logger.info("Info about the posts: {} ", (Object) result);
        return posts.stream()
                .map(post -> new MainPostDTO(post.getPublicId(), post.getUser().getUsername(), post.getDescription(), post.getPicture(), post.getCreationDate(), post.getComments()))
                .collect(Collectors.toList());
    }

    public List<MainPostDTO> getPostByPublicId(UUID userPublicId) {
        List<Post> postsByUserId = postRepository.findAllByUserPublicId(userPublicId);

        MainPostDTO[] result = restTemplate.getForObject("http://localhost:8080/api/posts", MainPostDTO[].class);
        logger.info("Info about the posts by searched user: {}, user id: {} ", result, userPublicId);
        //add comments to the posts, and user
        return postsByUserId.stream()
                .map(post -> new MainPostDTO(post.getPublicId(), post.getUser().getUsername(), post.getDescription(), post.getPicture(), post.getCreationDate()))
                .collect(Collectors.toList());
    }

}