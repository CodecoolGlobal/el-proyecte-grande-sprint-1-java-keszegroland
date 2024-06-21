package com.codecool.backend.dao.model;

import java.time.LocalDateTime;

public record MainPagePost(String username, String description, String picture, LocalDateTime creationDate) {
}
