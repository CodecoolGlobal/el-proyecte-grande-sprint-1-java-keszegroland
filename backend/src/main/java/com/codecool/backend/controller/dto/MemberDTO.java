package com.codecool.backend.controller.dto;

import com.codecool.backend.model.MemberRole;

import java.util.Set;
import java.util.UUID;

public record MemberDTO(UUID publicId, String firstName, String lastName, String username, String email,
                        Set<MemberRole> roles) {
}
