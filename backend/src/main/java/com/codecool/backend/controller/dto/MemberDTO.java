package com.codecool.backend.controller.dto;

import java.util.UUID;

public record MemberDTO(UUID publicId, String firstName, String lastName, String username, String email) {
}