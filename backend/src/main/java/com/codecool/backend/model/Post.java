package com.codecool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @UuidGenerator
    private UUID publicId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    private String description;
    @Lob
    private byte[] picture;
    private LocalDateTime creationDate = LocalDateTime.now();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
    private int numOfReport;
}
