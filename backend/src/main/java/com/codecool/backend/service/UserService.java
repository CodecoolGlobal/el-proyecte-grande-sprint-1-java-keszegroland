package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.controller.dto.UserDTO;
import com.codecool.backend.controller.dto.UserLoginDTO;
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

    public UserDTO getUserById(int id) {
        User user = userDAO.getUserById(id);
        return convertUserToDTO(user);
    }

    public boolean createNewUser(NewUserDTO user) {
        return userDAO.createNewUser(user);
    }

    public UserDTO loginUser(UserLoginDTO loginUser) {
        User user = userDAO.loginUser(loginUser);
        return convertUserToDTO(user);
    }

    private UserDTO convertUserToDTO(User user) {
        return new UserDTO(user.userId(), user.firstName(), user.lastName(), user.username(), user.email());
    }
}
