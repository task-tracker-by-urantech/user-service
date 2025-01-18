package com.urantech.userservice.model.entity.task;

import com.urantech.userservice.model.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "done", nullable = false)
    private boolean done = false;

    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return done == task.done
                && Objects.equals(id, task.id)
                && Objects.equals(description, task.description)
                && Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, done, user);
    }

    public Task(String description, User user) {
        this.description = description;
        this.user = user;
    }
}
