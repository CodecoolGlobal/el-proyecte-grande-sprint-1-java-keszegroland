package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.NewUserDTO;
import com.codecool.backend.dao.model.User;


public interface UserDAO {
    User getUserById(int id);

    boolean createNewUser(NewUserDTO user);
}
