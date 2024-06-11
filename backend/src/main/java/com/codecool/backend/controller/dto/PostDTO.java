package com.codecool.backend.controller.dto;

import java.time.LocalDateTime;

public record PostDTO(int post_id, int user_id, String description, String picture, LocalDateTime creation_date) {
}
