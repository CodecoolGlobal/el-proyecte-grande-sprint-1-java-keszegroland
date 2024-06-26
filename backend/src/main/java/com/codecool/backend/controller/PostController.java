package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.MainPostDTO;
import com.codecool.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<MainPostDTO> getAllPosts() {
        return postService.getAllPosts();
    }
//
//    @GetMapping("/{userPublicId}")
//    public List<MainPostDTO> getPostsByUserId(@PathVariable UUID userPublicId) {
//        return postService.getPostByPublicId(userPublicId);
//    }

}

