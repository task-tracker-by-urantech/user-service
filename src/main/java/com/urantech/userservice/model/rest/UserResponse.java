package com.urantech.userservice.model.rest;

import lombok.Data;

@Data
public class UserResponse {
    private String email;
    private long tasksCount;

    public UserResponse(String email, long tasksCount) {
        this.email = email;
        this.tasksCount = tasksCount;
    }
}
