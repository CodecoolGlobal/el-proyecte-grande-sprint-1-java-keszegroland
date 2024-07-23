package com.codecool.backend.repository;

import com.codecool.backend.model.Comment;
import com.codecool.backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
