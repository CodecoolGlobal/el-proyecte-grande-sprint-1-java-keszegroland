package com.codecool.backend.controller.dto;

import java.time.LocalDateTime;

public record MainPostDTO(String username, String description, String picture, LocalDateTime creation_date) {
}
