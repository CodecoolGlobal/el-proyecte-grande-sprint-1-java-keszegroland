package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.MemberLoginDTO;
import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signUp")
    public UUID signUp(@RequestBody NewMemberDTO member) {
        return memberService.createNewMember(member);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberLoginDTO member) {
        return ResponseEntity.ok(memberService.loginMember(member));
    }

    @PutMapping("/promote/{username}")
    public ResponseEntity<Void> promoteUserToAdmin(@PathVariable String username) {
        return memberService.promoteUserToAdmin(username);
    }

}
