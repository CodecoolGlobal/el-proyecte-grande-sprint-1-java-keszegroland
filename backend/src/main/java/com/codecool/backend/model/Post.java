package com.codecool.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private UUID publicId;
    @ManyToOne
    @JoinColumn
    private AppUser user;
    private String description;
    private String picture;
    private LocalDateTime creationDate;


    public long getId() {
        return id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public AppUser getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
