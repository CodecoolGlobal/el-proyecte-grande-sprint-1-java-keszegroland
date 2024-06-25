package com.codecool.backend.controller;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.controller.dto.UserDTO;
import com.codecool.backend.controller.dto.UserLoginDTO;
import com.codecool.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

/*    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signUp")
    public boolean signUp(@RequestBody NewUserDTO user) {
        return userService.createNewUser(user);
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody UserLoginDTO user) {
        return userService.loginUser(user);
    }*/
}
