package com.codecool.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @UuidGenerator
    private UUID publicId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "memberRoles",
            joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    @JsonManagedReference
    private Set<MemberRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public void addRole(MemberRole role) {
        roles.add(role);
    }
}
