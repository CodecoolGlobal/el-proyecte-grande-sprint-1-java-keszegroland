package com.codecool.backend.repository;

import com.codecool.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPublicId(UUID publicId);

    List<Post> findAllByMemberPublicId(UUID memberPublicId);
//    @Query("SELECT * FROM posts WHERE num_of_")
    List<Post> findByNumOfReportGreaterThan(int numOfReports);
}
