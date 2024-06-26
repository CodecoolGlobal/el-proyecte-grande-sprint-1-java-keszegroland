package com.codecool.backend.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MainPostDTO(UUID publicId, String username, String description, String picture,
                          LocalDateTime creationDate, List<CommentDTO> commentList) {
}
