package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public AdminService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public List<PostDTO> getReportedPosts() {
        List<Post> reportedPosts = postRepository.findByNumOfReportGreaterThan(0);
        return reportedPosts.stream().map(post ->
                        new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), Arrays.toString(post.getPicture()), post.getCreationDate()))
                .toList();
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
}
