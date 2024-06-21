package com.codecool.backend.dao.model;

import java.time.LocalDateTime;

public record Post(int postId, int userId, String description, String picture, LocalDateTime creationDate) {
}
