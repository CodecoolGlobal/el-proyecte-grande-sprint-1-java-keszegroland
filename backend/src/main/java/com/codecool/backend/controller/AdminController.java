package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/posts")
    public List<PostDTO> getReportedPosts() {
        return adminService.getReportedPosts();
    }


    @PutMapping("/promote/{username}")
    public ResponseEntity<Member> promoteUserToAdmin(@PathVariable String username) {
        return adminService.promoteUserToAdmin(username);
    }
}
