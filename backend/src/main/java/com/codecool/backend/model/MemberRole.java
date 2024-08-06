package com.codecool.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JsonBackReference
    private Set<Member> members;
}
