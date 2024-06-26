package com.codecool.backend.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDTO(UUID publicId, String comment, LocalDateTime creationDate, UUID postId, UUID userId) {
}
