package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.MemberLoginDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDTO getMemberById(UUID publicId) {
        Member member = memberRepository.findByPublicId(publicId);
        return convertMemberToDTO(member);
    }

    public UUID createNewMember(NewMemberDTO memberDTO) {
        try {
            Member member = new Member();
            member.setFirstName(memberDTO.firstName());
            member.setLastName(memberDTO.lastName());
            member.setUsername(memberDTO.username());
            member.setPassword(memberDTO.password());
            member.setEmail(memberDTO.email());
            memberRepository.save(member);
            return member.getPublicId();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error creating new member. " + e.getMessage());
        }
    }

    public MemberDTO loginMember(MemberLoginDTO loginMember) {
        Member member = memberRepository.findByUsernameAndPassword(loginMember.username(), loginMember.password());
        if (member == null) {
            return null;
        }
        return convertMemberToDTO(member);
    }

    private MemberDTO convertMemberToDTO(Member member) {
        return new MemberDTO(member.getPublicId(), member.getFirstName(), member.getLastName(), member.getUsername(), member.getEmail());
    }
}
