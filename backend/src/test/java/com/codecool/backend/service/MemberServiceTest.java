package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.exception.MemberAlreadyExistsException;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.MemberRole;
import com.codecool.backend.model.Role;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.MemberRoleRepository;
import com.codecool.backend.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberRoleRepository roleRepository;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.memberService = new MemberService(memberRepository, passwordEncoder, authenticationManager, jwtUtils, roleRepository);
    }

    @Test
    void testCreateMemberSuccessful_WhenUserRoleIsNotEmpty() {
        NewMemberDTO memberDTO = new NewMemberDTO("Nagy", "Lajos", "nagy_mancs", "Meow123", "mancs@gmail.com");
        MemberRole userRole = new MemberRole();
        userRole.setRole(Role.ROLE_USER);

        when(memberRepository.findByUsername(memberDTO.username())).thenReturn(Optional.empty());
        when(roleRepository.findByRole(Role.ROLE_USER)).thenReturn(Optional.of(userRole));

        UUID result = memberService.createNewMember(memberDTO);

        Mockito.verify(memberRepository).save(any(Member.class));
    }

    @Test
    void testCreateMember_WhenUserRoleIsEmpty() {
        NewMemberDTO memberDTO = new NewMemberDTO("Nagy", "Lajos", "nagy_mancs", "Meow123", "mancs@gmail.com");

        when(memberRepository.findByUsername(memberDTO.username())).thenReturn(Optional.empty());
        when(roleRepository.findByRole(Role.ROLE_USER)).thenReturn(Optional.empty());

        UUID result = memberService.createNewMember(memberDTO);

        Mockito.verify(roleRepository).save(any(MemberRole.class));
    }

    @Test
    void testCreateMemberFailed() {
        NewMemberDTO memberDTO = new NewMemberDTO("Nagy", "Lajos", "nagy_mancs", "Meow123", "mancs@gmail.com");
        Member member = new Member();
        member.setUsername(memberDTO.username());
        member.setPassword(member.getPassword());
        member.setEmail(memberDTO.email());
        member.setFirstName(memberDTO.firstName());
        member.setLastName(memberDTO.lastName());

        when(memberRepository.findByUsername(memberDTO.username())).thenReturn(Optional.of(member));
        assertThrows(MemberAlreadyExistsException.class, () -> memberService.createNewMember(memberDTO));
    }
}
