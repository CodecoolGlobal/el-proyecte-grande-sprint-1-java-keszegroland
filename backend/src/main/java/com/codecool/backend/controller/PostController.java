package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{memberPublicId}")
    public List<PostDTO> getPostsByMemberId(@PathVariable UUID memberPublicId) {
        return postService.getPostsByMemberPublicId(memberPublicId);
    }

    @PostMapping
    public UUID createPost(@RequestBody NewPostDTO postDTO, @RequestHeader("Authorization") String token ) {
        return postService.createNewPost(postDTO, token);
    }

    @PatchMapping("/report")
    public ResponseEntity<Void> reportPost(@RequestBody UUID postPublicId) {
        postService.reportPost(postPublicId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

