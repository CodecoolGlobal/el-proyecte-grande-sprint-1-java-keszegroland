package com.codecool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "memberRole")
@Setter
@Getter
public class MemberRole {

    @Id
    @GeneratedValue
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Role role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Member> members;

    public MemberRole() {
    }

    public MemberRole(Role role) {
        this.role = role;
        this.members = new HashSet<>();
    }
}
