package com.codecool.backend.repository;

import com.codecool.backend.model.MemberRole;
import com.codecool.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
    Optional<MemberRole> findByRole(Role role);
}
