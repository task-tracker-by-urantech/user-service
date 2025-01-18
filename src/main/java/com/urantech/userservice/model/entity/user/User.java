package com.urantech.userservice.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urantech.userservice.model.entity.task.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserAuthority> authorities = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
