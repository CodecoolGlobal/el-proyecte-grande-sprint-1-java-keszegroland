package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberLoginDTO;
import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.exception.MemberAlreadyExistsException;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.MemberRole;
import com.codecool.backend.model.Role;
import com.codecool.backend.model.payload.JwtResponse;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.MemberRoleRepository;
import com.codecool.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, MemberRoleRepository memberRoleRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.memberRoleRepository = memberRoleRepository;
    }

    public UUID createNewMember(NewMemberDTO memberDTO) {
        if (memberRepository.findByUsername(memberDTO.username()).isPresent()) {
            throw new MemberAlreadyExistsException(format("member %s already exists", memberDTO.username()));
        }

        Member member = createMemberEntity(memberDTO);
        Optional<MemberRole> userRole = memberRoleRepository.findByRole(Role.ROLE_USER);

        if (userRole.isEmpty()) {
            MemberRole newMemberRole = new MemberRole();
            newMemberRole.setRole(Role.ROLE_USER);
            member.getRoles().add(newMemberRole);
            memberRoleRepository.save(newMemberRole);
        } else {
            member.getRoles().add(userRole.get());
        }
        memberRepository.save(member);
        return member.getPublicId();
    }

    private Member createMemberEntity(NewMemberDTO memberDTO) {
        Member member = new Member();
        member.setFirstName(memberDTO.firstName());
        member.setLastName(memberDTO.lastName());
        member.setUsername(memberDTO.username());
        member.setPassword(passwordEncoder.encode(memberDTO.password()));
        member.setEmail(memberDTO.email());
        return member;
    }

    public JwtResponse loginMember(MemberLoginDTO loginMember) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginMember.username(), loginMember.password()));
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }

}
