package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.controller.dto.ReportDTO;
import com.codecool.backend.exception.MemberIsAlreadyReportedException;
import com.codecool.backend.exception.MemberIsNotFoundException;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.Report;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, MemberRepository memberRepository, ReportRepository reportRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.reportRepository = reportRepository;
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
    public UUID createNewPost(NewPostDTO newPostDTO) {
        try {
            Post post = new Post();
            Member member = findLoginMember();
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

    private Member findLoginMember() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberIsNotFoundException("Member could not be found in the database."));
    }

    @Transactional
    public void reportPost(ReportDTO reportDto) {
        Post post = postRepository.findByPublicId(reportDto.postPublicId());
        Member member = findLoginMember();
        if (reportRepository.findReportByMemberAndPostId(member, post.getId()).isPresent()) {
            throw new MemberIsAlreadyReportedException("Member already have a report in this post.");
        }
            Report report = new Report();
            report.setMember(member);
            report.setPost(post);
            report.setReasonOfReport(reportDto.reason());
            reportRepository.save(report);
            post.setNumOfReport(post.getReports().size());
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
        return memberRepository.findByPublicId(memberPublicId).orElseThrow(()-> new MemberIsNotFoundException("Member could not be found in the database."));
    }

    private PostDTO convertPostToDTO(Post post) {
        return new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate());
    }

}