package com.urantech.userservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("truncate table users, user_authority, task restart identity cascade");
        jdbcTemplate.execute("insert into users (id, task_id, email, password, enabled) " +
                "values (12345, null, 'j.dewar@gmail.com', 'pass', true)");
    }

    @Test
    void shouldReturnCorrectHttpStatusWhenGetAllUsersWithTasks() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/api/admin/users");

        // when
        mockMvc.perform(requestBuilder)
                // then
                .andExpect(status().isOk());
    }
}
