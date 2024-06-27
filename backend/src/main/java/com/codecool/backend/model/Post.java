package com.codecool.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private UUID publicId = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String description;
    @Lob
    private byte[] picture;
    private LocalDateTime creationDate = LocalDateTime.now();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;


    public UUID getPublicId() {
        return publicId;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
