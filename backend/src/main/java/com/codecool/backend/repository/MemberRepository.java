package com.codecool.backend.repository;

import com.codecool.backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByPublicId(UUID publicId);

    Member findByUsernameAndPassword(String username, String password);
}
