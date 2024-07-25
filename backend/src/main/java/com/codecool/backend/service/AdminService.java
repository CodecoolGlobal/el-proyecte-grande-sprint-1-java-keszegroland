package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.exception.MemberIsNotFoundException;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.Role;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public AdminService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public List<PostDTO> getReportedPosts() {
        List<Post> reportedPosts = postRepository.findByNumOfReportGreaterThan(0);
        return reportedPosts.stream().map(post ->
                        new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate()))
                .toList();
    }

    private String convertImageToBase64(byte[] picture) {
        String base64Image = null;
        if (picture != null) {
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(picture);
        }
        return base64Image;
    }

    private MemberDTO convertMemberToDTO(Member member) {
        return new MemberDTO(member.getPublicId(), member.getFirstName(), member.getLastName(), member.getUsername(), member.getEmail(), member.getRoles());
    }

    public List<MemberDTO> getAllMember() {
        return memberRepository.findAll()
                .stream()
                .map(this::convertMemberToDTO)
                .toList();
    }

    public ResponseEntity<Member> promoteUserToAdmin(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new MemberIsNotFoundException("Member not found"));
        member.addRole(Role.ROLE_ADMIN);
        memberRepository.save(member);
        return ResponseEntity.ok(member);
    }

    public void deleteMemberByPublicId(UUID publicId) {
        memberRepository.deleteByPublicId(publicId);
    }

    public void deletePostByPublicId(UUID publicId) {
        postRepository.deleteByPublicId(publicId);
    }
}
