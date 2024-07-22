package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.MemberLoginDTO;
import com.codecool.backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public MemberDTO getMembers(@PathVariable UUID id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/signUp")
    public UUID signUp(@RequestBody NewMemberDTO member) {
        return memberService.createNewMember(member);
    }

    @PostMapping("/login")
    public MemberDTO loginMember(@RequestBody MemberLoginDTO member) {
        return memberService.loginMember(member);
    }
}
