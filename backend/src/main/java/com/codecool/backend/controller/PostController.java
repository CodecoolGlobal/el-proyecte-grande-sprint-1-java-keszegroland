package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{userID}")
    public List<PostDTO> getAllPostsByUserId(@PathVariable int userID) {
        return postService.getAllPostsByUserId(userID);
    }

    @PostMapping("/{user_id}")
    public boolean createPost(@PathVariable int user_id, @RequestBody NewPostDTO postDTO) {
        return  postService.createNewPost(postDTO, user_id);
    }
}
