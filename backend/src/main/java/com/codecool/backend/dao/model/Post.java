package com.codecool.backend.dao.model;

import java.time.LocalDateTime;

public record Post(int post_id, int user_id, String description, String picture, LocalDateTime creation_date) {
}
