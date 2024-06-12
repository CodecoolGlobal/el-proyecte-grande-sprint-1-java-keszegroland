package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.dao.UserDAO;
import com.codecool.backend.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public boolean createNewUser(NewUserDTO user) {
        return userDAO.createNewUser(user);
    }
}
