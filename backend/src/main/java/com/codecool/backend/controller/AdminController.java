package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getAllMember")
    public List<MemberDTO> getAllMember() {
        return adminService.getAllMember();
    }

    @GetMapping("/posts")
    public List<PostDTO> getReportedPosts() {
        return adminService.getReportedPosts();
    }

    @PutMapping("/promote/{username}")
    public MemberDTO promoteUserToAdmin(@PathVariable String username) {
        return adminService.promoteUserToAdmin(username);
    }

    @DeleteMapping("/deleteMember/{publicId}")
    public UUID deleteMemberByPublicId(@PathVariable UUID publicId) {
        adminService.deleteMemberByPublicId(publicId);
        return publicId;
    }

    @DeleteMapping("/deletePost/{publicId}")
    public UUID deletePostByPublicId(@PathVariable UUID publicId) {
        adminService.deletePostByPublicId(publicId);
        return publicId;
    }


}
