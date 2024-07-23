package com.codecool.backend.service;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.MemberLoginDTO;
import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.exception.MemberAlreadyExistsException;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Role;
import com.codecool.backend.model.payload.JwtResponse;
import com.codecool.backend.repository.MemberRepository;
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

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public UUID createNewMember(NewMemberDTO memberDTO) {
        //TODO nem kell try catch hanem controller adviser!
        try {
            if (memberRepository.findByUsername(memberDTO.username()).isPresent()) {
                throw new MemberAlreadyExistsException(format("member %s already exists", memberDTO.username()));
            }
            Member member = new Member();
            memberRepository.save(setMemberData(member, memberDTO, Role.ROLE_USER));
            return member.getPublicId();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error creating new member. " + e.getMessage());
        }
    }

    private Member setMemberData(Member member, NewMemberDTO memberDTO, Role role){
        member.setFirstName(memberDTO.firstName());
        member.setLastName(memberDTO.lastName());
        member.setUsername(memberDTO.username());
        member.setPassword(passwordEncoder.encode(memberDTO.password()));
        member.setEmail(memberDTO.email());
        member.addRole(role);
        return member;
    }

    public JwtResponse loginMember(MemberLoginDTO loginMember) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginMember.username(), loginMember.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }

    private MemberDTO convertMemberToDTO(Member member) {
        return new MemberDTO(member.getPublicId(), member.getFirstName(), member.getLastName(), member.getUsername(), member.getEmail());
    }
}
