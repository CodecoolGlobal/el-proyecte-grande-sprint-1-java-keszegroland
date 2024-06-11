package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{userID}")
    public List<PostDTO> getAllPostsByUserId(@PathVariable int userID) {
        return postService.getAllPostsByUserId(userID);
    }
}
