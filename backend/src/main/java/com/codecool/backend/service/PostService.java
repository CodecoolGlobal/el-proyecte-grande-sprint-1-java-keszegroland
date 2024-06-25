package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MainPostDTO;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private RestTemplate restTemplate;

    @Autowired
    public PostService(RestTemplate restTemplate, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.postRepository = postRepository;
    }

    private List<MainPostDTO> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return null;
    }

}
