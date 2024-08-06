package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.exception.MemberIsNotFoundException;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.MemberRole;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.Role;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.MemberRoleRepository;
import com.codecool.backend.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    public AdminService(PostRepository postRepository, MemberRepository memberRepository, MemberRoleRepository memberRoleRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
    }

    @Transactional
    public List<PostDTO> getReportedPosts() {
        List<Post> reportedPosts = postRepository.findByNumOfReportGreaterThan(0);
        return reportedPosts.stream().map(post -> new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate())).toList();
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
        return memberRepository.findAll().stream().map(this::convertMemberToDTO).toList();
    }

    @Transactional
    public MemberDTO promoteUserToAdmin(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new MemberIsNotFoundException("Member not found"));

        Optional<MemberRole> adminRole = memberRoleRepository.findByRole(Role.ROLE_ADMIN);

        if (adminRole.isEmpty()) {
            MemberRole newMemberRole = new MemberRole();
            newMemberRole.setRole(Role.ROLE_ADMIN);
            member.getRoles().add(newMemberRole);
            memberRoleRepository.save(newMemberRole);
        } else {
            member.getRoles().add(adminRole.get());
            memberRepository.save(member);
        }
        return convertMemberToDTO(member);
    }

    @Transactional
    public void deleteMemberByPublicId(UUID publicId) {
        memberRepository.deleteByPublicId(publicId);
    }

    @Transactional
    public void deletePostByPublicId(UUID publicId) {
        postRepository.deleteByPublicId(publicId);
    }
}
