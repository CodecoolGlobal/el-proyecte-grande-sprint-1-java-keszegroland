package com.codecool.backend.dao;

import com.codecool.backend.dao.model.User;

import java.util.Set;

public interface UserDAO {
    Set<User> getAllUsers();
}
