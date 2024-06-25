package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.MainPostDTO;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

/*    @GetMapping
    public List<MainPostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{userId}")
    public List<PostDTO> getAllPostsByUserId(@PathVariable int userId) {
        return postService.getAllPostsByUserId(userId);
    }

    @PostMapping("/{userId}")
    public boolean createPost(@PathVariable int userId, @RequestBody NewPostDTO postDTO) {
        return  postService.createNewPost(postDTO, userId);
    }*/
}

