package com.codecool.backend.service;

import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {
    private final PostRepository postRepository;

    @Autowired
    public AdminService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getReportedPosts() {
        List<Post> reportedPosts = postRepository.findByNumOfReportGreaterThan(0);
        return reportedPosts.stream().map(post ->
                new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), Arrays.toString(post.getPicture()), post.getCreationDate()))
                .toList();
    }
}
