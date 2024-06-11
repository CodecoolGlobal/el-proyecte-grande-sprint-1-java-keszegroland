package com.codecool.backend.dao.model;

public record User(int userId, String firstName, String lastName, String username, String password, String email) {
}
