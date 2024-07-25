package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.MemberDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
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

}
