package com.codecool.backend.repository;

import com.codecool.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByPublicId(UUID publicId);
    AppUser findByUsernameAndPassword(String username, String password);
}
