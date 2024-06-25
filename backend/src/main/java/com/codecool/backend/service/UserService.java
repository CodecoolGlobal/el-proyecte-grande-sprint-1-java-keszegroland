package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.controller.dto.UserDTO;
import com.codecool.backend.controller.dto.UserLoginDTO;
import com.codecool.backend.repository.UserRepository;
import com.codecool.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

/*    public UserDTO getUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return convertUserToDTO(user.get());
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
    }*/
}
