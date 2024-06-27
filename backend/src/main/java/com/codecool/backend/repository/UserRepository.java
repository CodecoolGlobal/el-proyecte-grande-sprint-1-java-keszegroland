package com.codecool.backend.repository;

import com.codecool.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPublicId(UUID publicId);

    User findByUsernameAndPassword(String username, String password);
}
