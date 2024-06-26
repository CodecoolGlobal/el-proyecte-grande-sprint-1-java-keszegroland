package com.codecool.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private UUID commentPublicId = UUID.randomUUID();
    private String comment;
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public UUID getCommentPublicId() {
        return commentPublicId;
    }

    public long getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setCommentPublicId(UUID CommentPublicId) {
        this.commentPublicId = CommentPublicId;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
