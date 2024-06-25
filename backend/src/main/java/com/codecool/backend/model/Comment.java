package com.codecool.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private UUID CommentPublicId = UUID.randomUUID();
    private String comment;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    private LocalDateTime creationDate;

    public UUID getCommentPublicId() {
        return CommentPublicId;
    }

    public long getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setCommentPublicId(UUID CommentPublicId) {
        this.CommentPublicId = CommentPublicId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
