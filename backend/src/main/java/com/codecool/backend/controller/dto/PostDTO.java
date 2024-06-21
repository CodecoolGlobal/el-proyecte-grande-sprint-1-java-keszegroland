package com.codecool.backend.controller.dto;

import java.time.LocalDateTime;

public record PostDTO(int postId, int userId, String description, String picture, LocalDateTime creationDate) {
}
