package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.MemberRole;
import com.codecool.backend.model.Role;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.MemberRoleRepository;
import com.codecool.backend.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationTest.properties")
class AdminControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtUtils jwtUtils;

    private Member member1;
    private Member member2;
    private MemberRole userRole;
    private MemberRole adminRole;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        memberRoleRepository.deleteAll();

        userRole = new MemberRole();
        userRole.setRole(Role.ROLE_USER);
        memberRoleRepository.save(userRole);

        adminRole = new MemberRole();
        adminRole.setRole(Role.ROLE_ADMIN);
        memberRoleRepository.save(adminRole);

        member1 = new Member();
        member1.setUsername("member1");
        member1.setPassword(passwordEncoder.encode("member1"));
        member1.setEmail("member1@member1.com");
        member1.setFirstName("member1");
        member1.setLastName("member1");
        member1.addRole(userRole);
        member1.addRole(adminRole);
        memberRepository.save(member1);

        member2 = new Member();
        member2.setUsername("member2");
        member2.setPassword(passwordEncoder.encode("member2"));
        member2.setEmail("member2@member2.com");
        member2.setFirstName("member2");
        member2.setLastName("member2");
        member2.addRole(userRole);
        memberRepository.save(member2);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void givenMembers_WhenGetAllMembers_ThenReturnMembersList() throws Exception {
        List<MemberDTO> memberDTOList = List.of(
                new MemberDTO(member1.getPublicId(), member1.getFirstName(), member1.getLastName(), member1.getUsername(), member1.getEmail(), member1.getRoles()),
                new MemberDTO(member2.getPublicId(), member2.getFirstName(), member2.getLastName(), member2.getUsername(), member2.getEmail(), member2.getRoles())
        );

        mockMvc.perform(get("/api/admin/getAllMember")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(memberDTOList)));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void givenMember_WhenPromoteToAdmin_ThenReturnPromotedMember() throws Exception {
        Member promoteMember = memberRepository.findByUsername(member2.getUsername()).orElseThrow();
        Set<MemberRole> promoteMemberRoles = promoteMember.getRoles();

        assertEquals(userRole.getRole(), promoteMemberRoles.stream().findAny().get().getRole());

        MemberDTO expectedPromotedMemberDTO = new MemberDTO(member2.getPublicId(), member2.getFirstName(), member2.getLastName(), member2.getUsername(), member2.getEmail(), Set.of(userRole, adminRole));

        mockMvc.perform(put("/api/admin/promote/{username}", member2.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedPromotedMemberDTO)));

        Member promotedMember = memberRepository.findByUsername(member2.getUsername()).orElseThrow();

        Set<Role> actualRoles = new HashSet<>();
        promotedMember.getRoles().stream().map(MemberRole::getRole).forEach(actualRoles::add);
        assertEquals(Set.of(userRole.getRole(), adminRole.getRole()), actualRoles);
    }

}