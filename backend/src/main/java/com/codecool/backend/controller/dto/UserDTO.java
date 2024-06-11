package com.codecool.backend.controller.dto;

public record UserDTO(int userId, String firstName, String lastName, String username, String password, String email) {
}
