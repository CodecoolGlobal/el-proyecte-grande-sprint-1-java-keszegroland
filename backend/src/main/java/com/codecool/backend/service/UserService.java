package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.controller.dto.UserDTO;
import com.codecool.backend.controller.dto.UserLoginDTO;
import com.codecool.backend.repository.UserRepository;
import com.codecool.backend.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(UUID publicId) {
        AppUser user = userRepository.findByPublicId(publicId);
        return convertUserToDTO(user);
    }

    public UUID createNewUser(NewUserDTO userDTO) {
        AppUser user = new AppUser();
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        userRepository.save(user);
        return user.getPublicId();
    }

    public UserDTO loginUser(UserLoginDTO loginUser) {
        AppUser user = userRepository.findByUsernameAndPassword(loginUser.username(), loginUser.password());
        if (user == null) {
            return null;
        }
        return convertUserToDTO(user);
    }

    private UserDTO convertUserToDTO(AppUser user) {
        return new UserDTO(user.getPublicId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
    }
}
