package com.codecool.backend.repository;

import com.codecool.backend.model.Comment;
import com.codecool.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> getCommentsForPostByPostId(Long postId);

}
