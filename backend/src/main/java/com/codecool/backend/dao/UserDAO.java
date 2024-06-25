package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.controller.dto.UserLoginDTO;


public interface UserDAO {
    User getUserById(int id);

    boolean createNewUser(NewUserDTO user);

    User loginUser(UserLoginDTO user);
}
