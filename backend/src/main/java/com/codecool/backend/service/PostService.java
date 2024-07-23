package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.security.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final JwtUtils jwtUtils;

    @Autowired
    public PostService(PostRepository postRepository, MemberRepository memberRepository, JwtUtils jwtUtils) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.jwtUtils = jwtUtils;
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::convertPostToDTO).toList();
    }

    public List<PostDTO> getPostsByMemberPublicId(UUID memberPublicId) {
        List<Post> postsByMemberId = postRepository.findAllByMemberPublicId(memberPublicId);
        return postsByMemberId.stream().map(this::convertPostToDTO).toList();
    }

    @Transactional
    public UUID createNewPost(NewPostDTO newPostDTO, String token) {
        try {
            Post post = new Post();
            String username = jwtUtils.getUsernameFromJwtToken(token.substring(7));
            //TODO custom exception for the orElseThrow
            //TODO ADVISER
            Member member = memberRepository.findByUsername(username).orElseThrow();
            post.setMember(member);
            post.setDescription(newPostDTO.description());
            post.setPicture(convertBase64Image(newPostDTO));
            postRepository.save(post);
            return post.getPublicId();
        } catch (RuntimeException e) {
            logger.error("Error creating a new post: {} ", e.getMessage());
            throw new RuntimeException("Error creating a new post. " + e.getMessage());
        }
    }

    public void reportPost(UUID id) {
        Post post = postRepository.findByPublicId(id);
        post.setNumOfReport(post.getNumOfReport() + 1);
        postRepository.save(post);
    }

    private byte[] convertBase64Image(NewPostDTO newPostDTO) {
        String pictureBase64Data = newPostDTO.picture();
        if (pictureBase64Data != null && !pictureBase64Data.isEmpty()) {
            return Base64.getDecoder().decode(pictureBase64Data);
        }
        return null;
    }

    private String convertImageToBase64(byte[] picture) {
        String base64Image = null;
        if (picture != null) {
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(picture);
        }
        return base64Image;
    }

    private Member getMemberByPublicId(UUID memberPublicId) {
        return memberRepository.findByPublicId(memberPublicId);
    }

    private PostDTO convertPostToDTO(Post post) {
        return new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate());
    }

}