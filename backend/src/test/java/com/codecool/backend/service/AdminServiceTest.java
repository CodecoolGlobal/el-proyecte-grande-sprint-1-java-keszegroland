package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.MemberRole;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.Role;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.MemberRoleRepository;
import com.codecool.backend.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AdminServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberRoleRepository memberRoleRepository;
    private AdminService adminService;

    private Post post;
    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.adminService = new AdminService(postRepository, memberRepository, memberRoleRepository);

        member = new Member();
        member.setFirstName("Nagy");
        member.setLastName("Lajos");
        member.setUsername("nagy_mancs");
        member.setPassword("Meow123");
        member.setEmail("mancs@gmail.com");
        memberRepository.save(member);
        member.setPublicId(UUID.fromString("efc544d8-9ed6-4dfb-968b-1b939d202ee8"));

        post = new Post();
        post.setDescription("meow");
        post.setPicture("".getBytes());
        post.setMember(member);
        post.setNumOfReport(2);
        postRepository.save(post);
    }

    private String convertImageToBase64(byte[] picture) {
        String base64Image = null;
        if (picture != null) {
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(picture);
        }
        return base64Image;
    }

    @Test
    void testGetReportedPosts() {
        when(postRepository.findByNumOfReportGreaterThan(0)).thenReturn(List.of(post));
        List<Post> reported = List.of(post);
        List<PostDTO> expectedReportedPosts = reported.stream().map(post -> new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate())).toList();
        List<PostDTO> actualReportedPosts = adminService.getReportedPosts();
        assertEquals(expectedReportedPosts, actualReportedPosts);
    }

    @Test
    void testGetAllMember() {
        List<Member> members = List.of(member);
        when(memberRepository.findAll()).thenReturn(members);
        List<MemberDTO> expectedMembers = members.stream()
                .map(member -> new MemberDTO(member.getPublicId(), member.getFirstName(), member.getLastName(), member.getUsername(), member.getEmail(), member.getRoles()))
                .toList();
        List<MemberDTO> actualMembers = adminService.getAllMember();
        assertEquals(expectedMembers, actualMembers);
    }

    @Test
    void testPromoteUserToAdmin_WhenUserDoesNotHaveAdminRole() {
        when(memberRepository.findByUsername("nagy_mancs")).thenReturn(Optional.of(member));
        when(memberRoleRepository.findByRole(Role.ROLE_ADMIN)).thenReturn(Optional.empty());
        MemberRole role = new MemberRole();
        role.setRole(Role.ROLE_ADMIN);
        member.addRole(role);
        MemberDTO expected = new MemberDTO(member.getPublicId(), member.getFirstName(), member.getLastName(),
                member.getUsername(), member.getEmail(), member.getRoles());
        MemberDTO actual = adminService.promoteUserToAdmin("nagy_mancs");
        assertEquals(expected, actual);
        Mockito.verify(memberRoleRepository).save(any(MemberRole.class));
    }

    @Test
    void testPromoteUserToAdmin_WhenUserHasAdminRole() {
        MemberRole role = new MemberRole();
        role.setRole(Role.ROLE_ADMIN);
        member.addRole(role);

        when(memberRepository.findByUsername("nagy_mancs")).thenReturn(Optional.of(member));
        when(memberRoleRepository.findByRole(Role.ROLE_ADMIN)).thenReturn(Optional.of(role));

        MemberDTO expected = new MemberDTO(member.getPublicId(), member.getFirstName(), member.getLastName(),
                member.getUsername(), member.getEmail(), member.getRoles());
        MemberDTO actual = adminService.promoteUserToAdmin("nagy_mancs");
        assertEquals(expected, actual);
    }
}
