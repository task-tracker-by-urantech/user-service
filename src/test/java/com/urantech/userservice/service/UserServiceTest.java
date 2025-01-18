package com.urantech.userservice.service;

import com.urantech.userservice.model.rest.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService service;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("truncate table users, user_authority, task restart identity cascade");
        jdbcTemplate.execute("insert into users (id, task_id, email, password, enabled) " +
                "values (12345, null, 'j.dewar@gmail.com', 'pass', true)");
    }

    @Test
    void shouldReturnUsersWithTasks() {
        // given
        jdbcTemplate.execute("insert into task (id, user_id, description, done) " +
                "values (12345, 12345, 'task', false)");

        // when
        List<UserResponse> actual = service.getUsersWithTasks();

        // then
        assertThat(actual.size(), equalTo(1));
        assertThat(actual.get(0).getTasksCount(), equalTo(1L));
    }

    @Test
    void shouldReturnEmptyListWhenUsersAreDisabled() {
        // given
        jdbcTemplate.execute("insert into task (id, user_id, description, done) " +
                "values (12345, 12345, 'task', false)");
        jdbcTemplate.execute("update users set task_id = 12345, enabled = false where id = 12345");

        // when
        List<UserResponse> actual = service.getUsersWithTasks();

        // then
        assertThat(actual.size(), equalTo(0));
    }
}
