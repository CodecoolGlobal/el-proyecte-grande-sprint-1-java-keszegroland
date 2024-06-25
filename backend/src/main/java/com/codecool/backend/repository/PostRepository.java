package com.codecool.backend.repository;

import com.codecool.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();
    Post findByPublicId(UUID publicId);
}
